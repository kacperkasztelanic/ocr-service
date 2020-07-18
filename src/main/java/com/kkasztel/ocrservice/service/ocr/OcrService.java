package com.kkasztel.ocrservice.service.ocr;

import com.kkasztel.ocrservice.exception.OcrException;

import io.vavr.control.Either;

public interface OcrService {

    Either<OcrException, String> doOcr();
}
