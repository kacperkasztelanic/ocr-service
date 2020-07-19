package com.kkasztel.ocrservice.exception;

public class OcrException extends RuntimeException {

    public OcrException(String message) {
        super(message);
    }

    public OcrException(Throwable cause) {
        super(cause);
    }
}
