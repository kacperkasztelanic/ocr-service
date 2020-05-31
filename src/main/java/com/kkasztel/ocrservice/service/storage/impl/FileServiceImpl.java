package com.kkasztel.ocrservice.service.storage.impl;

import com.kkasztel.ocrservice.config.AppProps;
import com.kkasztel.ocrservice.exception.FileStorageException;
import com.kkasztel.ocrservice.service.UuidSupplier;
import com.kkasztel.ocrservice.service.storage.FileService;
import com.kkasztel.ocrservice.service.storage.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vavr.control.Either;
import io.vavr.control.Option;

@Service
public class FileServiceImpl implements FileService {

    private final FileStorageService fileStorageService;
    private final UuidSupplier uuidSupplier;
    private final AppProps appProps;

    @Autowired
    public FileServiceImpl(FileStorageService fileStorageService, UuidSupplier uuidSupplier, AppProps appProps) {
        this.fileStorageService = fileStorageService;
        this.uuidSupplier = uuidSupplier;
        this.appProps = appProps;
    }

    @Override
    public Either<FileStorageException, String> save(byte[] bytes) {
        String id = uuidSupplier.get();
        return fileStorageService.store(bytes, id, appProps.getMinio().getBucket()).map(x -> id);
    }

    @Override
    public Either<FileStorageException, Option<byte[]>> load(String name) {
        return fileStorageService.load(name, appProps.getMinio().getBucket());
    }
}
