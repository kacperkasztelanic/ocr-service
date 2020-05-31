package com.kkasztel.ocrservice.web;

import com.kkasztel.ocrservice.config.AppProps;
import com.kkasztel.ocrservice.service.ResultService;
import com.kkasztel.ocrservice.service.UuidSupplier;
import com.kkasztel.ocrservice.service.model.Job;
import com.kkasztel.ocrservice.service.model.Result;
import com.kkasztel.ocrservice.service.storage.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;

import io.vavr.CheckedFunction1;
import lombok.extern.slf4j.Slf4j;

import static io.vavr.API.CheckedFunction;
import static io.vavr.API.Try;
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
    private final ResultService resultService;
    private final UuidSupplier uuidSupplier;
    private final AppProps appProps;

    @Autowired
    public OcrResource(FileService fileService, ResultService resultService, UuidSupplier uuidSupplier,
            AppProps appProps) {
        this.fileService = fileService;
        this.uuidSupplier = uuidSupplier;
        this.resultService = resultService;
        this.appProps = appProps;
    }

    @PostMapping("/")
    public ResponseEntity<URL> doOcr(MultipartFile file) {
        CheckedFunction1<Job, URL> urlFunction = CheckedFunction(//
                j -> linkTo(methodOn(OcrResource.class).check(j.getId())).toUri().toURL()//
        );
        String id = uuidSupplier.get();
        Instant now = Instant.now();
        return Try(file::getBytes)//
                .flatMap(b -> fileService.save(b).toTry())//
                .map(i -> Job.of(id, now, now.plus(Duration.ofDays(appProps.getStorageTime().getJobDays())), i))//
                .mapTry(urlFunction)//
                .onFailure(t -> log.error(t.getMessage(), t))//
                .map(u -> accepted().body(u))//
                .getOrElse(() -> status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> check(String id) {
        return resultService.findById(id)//
                .flatMap(Result::getResults)//
                .map(ResponseEntity::ok)//
                .getOrElse(() -> notFound().build());
    }
}
