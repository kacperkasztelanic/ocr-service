package com.kkasztel.ocrservice.exception;

public class FileNotExistException extends OcrException {

    public FileNotExistException(String fileName) {
        super("File not found: " + fileName);
    }
}
