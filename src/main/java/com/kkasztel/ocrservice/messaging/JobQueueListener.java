package com.kkasztel.ocrservice.messaging;

import com.kkasztel.ocrservice.service.ocr.OcrService;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import static com.kkasztel.ocrservice.config.RabbitConfig.JOB_LISTENER;
import static com.kkasztel.ocrservice.config.RabbitConfig.JOB_QUEUE;

@Slf4j
@Service
@RabbitListener(//
        queues = JOB_QUEUE,//
        id = JOB_LISTENER,//
        autoStartup = "false",//
        concurrency = "${app.messaging.concurrency}"//
)
public class JobQueueListener {

    private final OcrService ocrService;

    @Autowired
    public JobQueueListener(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @RabbitHandler
    public void handleExportRequest(String jobId) {
        ocrService.doOcr(jobId);
    }
}
