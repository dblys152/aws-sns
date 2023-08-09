package com.ys.order.adapter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ys.order.application.message.SnsMapping;
import com.ys.order.application.message.SqsMapping;
import com.ys.order.domain.event.OrderCompletedEvent;
import io.awspring.cloud.sns.core.SnsTemplate;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.awspring.cloud.sqs.support.converter.SqsMessagingMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AwsConfig {

    @Value("${aws.sns.ORDER_COMPLETED_TOPIC}")
    String ORDER_COMPLETED_TOPIC;

    @Value("${aws.sqs.FIRST_BENEFIT_CREATION_QUEUE}")
    String FIRST_BENEFIT_CREATION_QUEUE;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder().build();
    }

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder().build();
    }

    @Bean
    public SnsTemplate snsTemplate() {
        return new SnsTemplate(snsClient());
    }

    @Bean
    public SqsMessagingMessageConverter sqsMessagingMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        SqsMessagingMessageConverter messageConverter = new SqsMessagingMessageConverter();
        messageConverter.setObjectMapper(objectMapper);
        return messageConverter;
    }

    @Bean
    public SqsTemplate sqsTemplate() {
        return SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient())
                .messageConverter(sqsMessagingMessageConverter())
                .build();
    }

    @Bean
    public SnsMapping snsMapping(){
        SnsMapping mapping = new SnsMapping();
        mapping.add(OrderCompletedEvent.class.getName(), ORDER_COMPLETED_TOPIC);
        return mapping;
    }

    @Bean
    public SqsMapping sqsMapping(){
        SqsMapping mapping = new SqsMapping();
        mapping.add(OrderCompletedEvent.class.getName(), FIRST_BENEFIT_CREATION_QUEUE);
        return mapping;
    }
}
