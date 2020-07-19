package com.kkasztel.ocrservice.service.job;

import com.kkasztel.ocrservice.domain.entity.JobEntity;
import com.kkasztel.ocrservice.service.model.Job;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface JobMapper {

    JobEntity jobToJobEntity(Job job);

    Job jobEntityToJob(JobEntity jobEntity);
}
