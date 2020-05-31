package com.kkasztel.ocrservice.service.storage;

import com.kkasztel.ocrservice.exception.FileStorageException;

import io.vavr.control.Either;
import io.vavr.control.Option;

public interface FileStorageService {

    Either<FileStorageException, byte[]> store(byte[] file, String name, String directory);

    Either<FileStorageException, Option<byte[]>> load(String name, String directory);
}
