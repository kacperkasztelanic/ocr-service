package com.kkasztel.ocrservice.web;

import com.kkasztel.ocrservice.exception.FileStorageException;
import com.kkasztel.ocrservice.exception.FileTypeProbeException;
import com.kkasztel.ocrservice.exception.OcrException;
import com.kkasztel.ocrservice.service.job.JobService;
import com.kkasztel.ocrservice.service.model.Job;
import com.kkasztel.ocrservice.service.model.Result;
import com.kkasztel.ocrservice.service.result.ResultService;
import com.kkasztel.ocrservice.service.storage.FileService;
import com.kkasztel.ocrservice.service.uuid.UuidSupplier;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Clock;
import java.time.Instant;

import io.vavr.CheckedFunction1;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

import static io.vavr.API.CheckedFunction;
import static io.vavr.API.Try;
import static io.vavr.API.Tuple;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestController
@RequestMapping("/api/ocr")
public class OcrResource {

    private final FileService fileService;
    private final JobService jobService;
    private final ResultService resultService;
    private final UuidSupplier uuidSupplier;
    private final Clock clock;
    private final TikaConfig tika;

    @Autowired
    public OcrResource(FileService fileService, JobService jobService, ResultService resultService,
            UuidSupplier uuidSupplier, Clock clock, TikaConfig tika) {
        this.fileService = fileService;
        this.jobService = jobService;
        this.uuidSupplier = uuidSupplier;
        this.resultService = resultService;
        this.clock = clock;
        this.tika = tika;
    }

    @PostMapping("/")
    public ResponseEntity<URL> doOcr(MultipartFile file) {
        CheckedFunction1<Job, URL> urlFunction = CheckedFunction(//
                j -> linkTo(methodOn(OcrResource.class).check(j.getId())).toUri().toURL()//
        );
        String id = uuidSupplier.get();
        Instant now = clock.instant();
        return Try(file::getBytes)//
                .toEither().mapLeft(FileStorageException::new).mapLeft(OcrException.class::cast)//
                .flatMap(b -> fileService.save(b).mapLeft(OcrException.class::cast)//
                        .flatMap(i -> probeFileExtension(b).mapLeft(OcrException.class::cast).map(e -> Tuple(i, e)))//
                        .map(p -> Job.of(id, now, p._1, p._2))//
                )//
                .map(jobService::enqueue)//
                .flatMap(j -> Try(() -> urlFunction.apply(j))//
                        .toEither().mapLeft(OcrException::new)//
                )//
                .peekLeft(t -> log.error(t.getMessage(), t))//
                .map(u -> accepted().body(u))//
                .getOrElse(() -> status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> check(@PathVariable String id) {
        return resultService.findById(id)//
                .flatMap(Result::getResults)//
                .map(ResponseEntity::ok)//
                .getOrElse(() -> notFound().build());
    }

    private Either<FileTypeProbeException, String> probeFileExtension(byte[] file) {
        return Try(() -> new ByteArrayInputStream(file))//
                .mapTry(s -> tika.getMimeRepository().detect(s, new Metadata()))//
                .mapTry(t -> tika.getMimeRepository().forName(t.toString()))//
                .map(MimeType::getExtension)//
                .toEither().mapLeft(FileTypeProbeException::new);
    }
}
