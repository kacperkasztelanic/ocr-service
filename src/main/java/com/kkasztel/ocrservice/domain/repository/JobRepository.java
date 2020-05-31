package com.kkasztel.ocrservice.domain.repository;

import com.kkasztel.ocrservice.domain.entity.JobEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<JobEntity, String> {
}
