package com.kkasztel.ocrservice.service.job;

import com.kkasztel.ocrservice.service.model.Job;

import io.vavr.control.Option;

public interface JobService {

    Job enqueue(Job job);

    Option<Job> findById(String id);
}
