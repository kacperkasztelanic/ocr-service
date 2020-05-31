package com.kkasztel.ocrservice.service.impl;

import com.kkasztel.ocrservice.domain.repository.JobRepository;
import com.kkasztel.ocrservice.service.JobService;
import com.kkasztel.ocrservice.service.mapper.JobMapper;
import com.kkasztel.ocrservice.service.model.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vavr.control.Option;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository repository;
    private final JobMapper mapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public JobServiceImpl(JobRepository repository, JobMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Job save(Job job) {
        return mapper.jobEntityToJob(repository.save(mapper.jobToJobEntity(job)));
    }

    @Override
    public Option<Job> findById(String id) {
        return Option.ofOptional(repository.findById(id)).map(mapper::jobEntityToJob);
    }
}
