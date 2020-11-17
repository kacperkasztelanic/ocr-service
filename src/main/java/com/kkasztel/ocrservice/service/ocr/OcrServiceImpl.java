package com.kkasztel.ocrservice.service.ocr;

import com.kkasztel.ocrservice.exception.FileNotExistException;
import com.kkasztel.ocrservice.exception.JobNotExistException;
import com.kkasztel.ocrservice.exception.OcrException;
import com.kkasztel.ocrservice.service.job.JobService;
import com.kkasztel.ocrservice.service.model.Result;
import com.kkasztel.ocrservice.service.result.ResultService;
import com.kkasztel.ocrservice.service.storage.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

import io.vavr.control.Either;

import static java.util.function.Function.identity;

@Service
class OcrServiceImpl implements OcrService {

    private final OcrEngine ocrEngine;
    private final JobService jobService;
    private final ResultService resultService;
    private final FileService fileService;
    private final Clock clock;

    @Autowired
    public OcrServiceImpl(OcrEngine ocrEngine, JobService jobService, ResultService resultService,
            FileService fileService, Clock clock) {
        this.ocrEngine = ocrEngine;
        this.jobService = jobService;
        this.resultService = resultService;
        this.fileService = fileService;
        this.clock = clock;
    }

    @Override
    public Result doOcr(String jobId) {
        Instant instant = clock.instant();
        Result result = doOcrInternal(jobId).map(s -> Result.success(jobId, instant, s))//
                .mapLeft(e -> Result.failure(jobId, instant, e.getMessage()))//
                .getOrElseGet(identity());
        resultService.save(result);
        return result;
    }

    private Either<OcrException, String> doOcrInternal(String jobId) {
        return jobService.findById(jobId).toEither(() -> (OcrException) new JobNotExistException(jobId))//
                .flatMap(j -> fileService.load(j.getInput()).mapLeft(OcrException.class::cast)//
                        .flatMap(o -> o.toEither(() -> new FileNotExistException(j.getInput())))//
                        .flatMap(ocrEngine::doOcr)//
                );
    }
}
