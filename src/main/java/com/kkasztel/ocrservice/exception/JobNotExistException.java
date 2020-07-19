package com.kkasztel.ocrservice.exception;

public class JobNotExistException extends OcrException {

    public JobNotExistException(String jobId) {
        super("Job not found: " + jobId);
    }
}
