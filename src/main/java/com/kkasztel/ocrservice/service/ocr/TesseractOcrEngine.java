package com.kkasztel.ocrservice.service.ocr;

import com.kkasztel.ocrservice.exception.OcrException;

import org.springframework.stereotype.Service;

import io.vavr.control.Either;

import static io.vavr.API.Right;

@Service
class TesseractOcrEngine implements OcrEngine {

    //TODO
    @Override
    public Either<OcrException, String> doOcr(byte[] data) {
        return Right("foo");
    }
}
