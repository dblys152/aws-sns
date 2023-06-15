package com.ys.order.adapter.config;

import com.ys.order.application.message.Mapping;
import com.ys.order.application.message.MessageSenderNameMapping;
import com.ys.order.domain.event.OrderCompletedEvent;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AwsConfig {

    @Value("${aws.sns.ORDER_COMPLETED_TOPIC}")
    String ORDER_COMPLETED_TOPIC;
    @Value("${aws.sqs.FIRST_BENEFIT_CREATION_QUEUE}")
    String FIRST_BENEFIT_CREATION_QUEUE;

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient) {
        return SqsTemplate.newTemplate(sqsAsyncClient);
    }

    @Bean
    public Mapping sqsSenderNameMapping(){
        MessageSenderNameMapping mapping = new MessageSenderNameMapping();
        mapping.add(OrderCompletedEvent.class.getName(), ORDER_COMPLETED_TOPIC);
        mapping.add(OrderCompletedEvent.class.getName(), FIRST_BENEFIT_CREATION_QUEUE);
        return mapping;
    }
}
