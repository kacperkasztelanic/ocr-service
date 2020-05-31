package com.kkasztel.ocrservice.service.mapper;

import com.kkasztel.ocrservice.domain.entity.JobEntity;
import com.kkasztel.ocrservice.service.model.Job;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobMapperTest {

    private final JobMapper mapper = Mappers.getMapper(JobMapper.class);

    @Test
    void jobToEntityCorrect() {
        Job job = Job.of(UUID.randomUUID().toString(), Instant.now(), Instant.now(), "input");
        JobEntity jobEntity = mapper.jobToJobEntity(job);

        assertEquals(job.getId(), jobEntity.getId());
        assertEquals(job.getCreationTime(), jobEntity.getCreationTime());
        assertEquals(job.getExpirationTime(), jobEntity.getExpirationTime());
        assertEquals(job.getInput(), jobEntity.getInput());
    }
}
