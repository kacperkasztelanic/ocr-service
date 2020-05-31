package com.kkasztel.ocrservice.service.storage;

import com.kkasztel.ocrservice.exception.FileStorageException;

import io.vavr.control.Either;
import io.vavr.control.Option;

public interface FileService {

    Either<FileStorageException, String> save(byte[] bytes);

    Either<FileStorageException, Option<byte[]>> load(String name);
}
