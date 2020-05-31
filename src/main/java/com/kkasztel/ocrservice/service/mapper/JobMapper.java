package com.kkasztel.ocrservice.service.mapper;

import com.kkasztel.ocrservice.domain.entity.JobEntity;
import com.kkasztel.ocrservice.service.model.Job;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobEntity jobToJobEntity(Job job);

    Job jobEntityToJob(JobEntity jobEntity);
}
