package com.kkasztel.ocrservice.service.job;

import com.kkasztel.ocrservice.domain.repository.JobRepository;
import com.kkasztel.ocrservice.messaging.JobQueue;
import com.kkasztel.ocrservice.service.model.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vavr.control.Option;

@Service
class JobServiceImpl implements JobService {

    private final JobQueue jobQueue;
    private final JobRepository repository;
    private final JobMapper mapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public JobServiceImpl(JobQueue jobQueue, JobRepository repository, JobMapper mapper) {
        this.jobQueue = jobQueue;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Job enqueue(Job job) {
        return jobQueue.enqueueJob(mapper.jobEntityToJob(repository.save(mapper.jobToJobEntity(job))));
    }

    @Override
    public Option<Job> findById(String id) {
        return Option.ofOptional(repository.findById(id)).map(mapper::jobEntityToJob);
    }
}
