package com.kkasztel.ocrservice.service.job;

import com.kkasztel.ocrservice.domain.entity.JobEntity;
import com.kkasztel.ocrservice.service.model.Job;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Clock;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JobMapperTest {

    private final JobMapper mapper = Mappers.getMapper(JobMapper.class);
    private final Clock clock = Clock.systemUTC();

    @Test
    void jobToEntityCorrect() {
        Job job = Job.of(UUID.randomUUID().toString(), clock.instant(), "input", "png");
        JobEntity jobEntity = mapper.jobToJobEntity(job);

        assertEquals(job.getId(), jobEntity.getId());
        assertEquals(job.getCreationTime(), jobEntity.getCreationTime());
        assertEquals(job.getInput(), jobEntity.getInput());
    }
}
