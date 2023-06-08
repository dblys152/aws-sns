package com.ys.order.adapter.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.ys.order.application.message.Mapping;
import com.ys.order.application.message.MessageSenderNameMapping;
import com.ys.order.domain.event.OrderCompletedEvent;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${cloud.aws.credentials.access-key}")
    String AWS_ACCESS_KEY;
    @Value("${cloud.aws.credentials.secret-key}")
    String AWS_SECRET_KEY;
    @Value("${cloud.aws.region.static}")
    String REGION;

    @Value("${aws.sns.ORDER_COMPLETED_TOPIC}")
    String ORDER_COMPLETED_TOPIC;
    @Value("${aws.sqs.FIRST_BENEFIT_GENERATION_QUEUE}")
    String FIRST_BENEFIT_GENERATION_QUEUE;

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));
    }

    @Bean
    public NotificationMessagingTemplate getNotificationMessagingTemplate(AWSCredentialsProvider awsCredentialsProvider) {
        return new NotificationMessagingTemplate(AmazonSNSAsyncClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(REGION)
                .build());
    }

    @Bean
    public QueueMessagingTemplate getQueueMessagingTemplate(AWSCredentialsProvider awsCredentialsProvider) {
        return new QueueMessagingTemplate(AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(REGION)
                .build());
    }

    @Bean
    public Mapping sqsSenderNameMapping(){
        MessageSenderNameMapping mapping = new MessageSenderNameMapping();
        mapping.add(OrderCompletedEvent.class.getName(), ORDER_COMPLETED_TOPIC);
        mapping.add(OrderCompletedEvent.class.getName(), FIRST_BENEFIT_GENERATION_QUEUE);
        return mapping;
    }
}
