package com.kkasztel.ocrservice.domain.repository;

import com.kkasztel.ocrservice.domain.entity.ResultEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends MongoRepository<ResultEntity, String> {
}
