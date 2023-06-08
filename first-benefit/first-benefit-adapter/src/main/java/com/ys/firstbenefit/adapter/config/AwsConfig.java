package com.ys.firstbenefit.adapter.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsConfig {

    @Value("${cloud.aws.credentials.access-key}")
    String AWS_ACCESS_KEY;
    @Value("${cloud.aws.credentials.secret-key}")
    String AWS_SECRET_KEY;
    @Value("${cloud.aws.region.static}")
    String REGION;

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));
    }

    @Bean
    public AmazonSQS amazonSQS(AWSCredentialsProvider awsCredentialsProvider) {
        return AmazonSQSClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(REGION)
                .build();
    }
}
