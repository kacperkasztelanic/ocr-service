package com.kkasztel.ocrservice.service.ocr;

import com.kkasztel.ocrservice.service.model.Result;

public interface OcrService {

    Result doOcr(String jobId);
}
