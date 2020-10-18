package com.kkasztel.ocrservice.messaging;

import com.kkasztel.ocrservice.service.model.Job;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.kkasztel.ocrservice.config.RabbitConfig.JOB_EXCHANGE;
import static com.kkasztel.ocrservice.config.RabbitConfig.JOB_KEY;

@Service
class RabbitJobQueue implements JobQueue {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitJobQueue(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Job enqueueJob(Job job) {
        rabbitTemplate.convertAndSend(JOB_EXCHANGE, JOB_KEY, job.getId());
        return job;
    }
}
