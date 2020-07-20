package com.kkasztel.ocrservice.exception;

public class FileStorageException extends OcrException {

    public FileStorageException(Throwable cause) {
        super(cause);
    }

    public FileStorageException(String message) {
        super(message);
    }
}
