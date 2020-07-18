package com.kkasztel.ocrservice.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.vavr.jackson.datatype.VavrModule;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()//
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)//
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)//
                .configure(SerializationFeature.INDENT_OUTPUT, true)//
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)//
                .registerModule(new VavrModule())//
                .registerModule(new JavaTimeModule())//
                .registerModule(new Jdk8Module())//
                .registerModule(new ParameterNamesModule());
    }
}
