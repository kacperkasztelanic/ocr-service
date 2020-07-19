package com.kkasztel.ocrservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String JOB_EXCHANGE = "job-exchange";
    public static final String JOB_QUEUE = "job-queue";
    public static final String JOB_KEY = "job-key";
    public static final String JOB_LISTENER = "job-listener";

    @Bean
    public DirectExchange directJobExchange() {
        return new DirectExchange(JOB_EXCHANGE);
    }

    @Bean
    public Queue jobQueue() {
        return QueueBuilder.durable(JOB_QUEUE).build();
    }

    @Bean
    public Binding bindingExport(DirectExchange directJobExchange, Queue jobQueue) {
        return BindingBuilder.bind(jobQueue).to(directJobExchange).with(JOB_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper mapper, ClassMapper classMapper) {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(mapper);
        converter.setClassMapper(classMapper);
        return converter;
    }

    @Bean
    public ClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("*");
        return classMapper;
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }
}
