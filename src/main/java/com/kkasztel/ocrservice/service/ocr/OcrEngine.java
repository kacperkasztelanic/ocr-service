package com.kkasztel.ocrservice.service.ocr;

import com.kkasztel.ocrservice.exception.OcrException;

import io.vavr.control.Either;

interface OcrEngine {

    Either<OcrException, String> doOcr(byte[] data);
}
