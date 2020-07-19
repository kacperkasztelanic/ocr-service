package com.kkasztel.ocrservice.messaging;

import com.kkasztel.ocrservice.service.model.Job;

public interface JobQueue {

    void enqueueJob(Job job);
}
