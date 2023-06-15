package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqsSender implements MessageSender<String> {

    private final Serializer<DomainEvent> serializer;
    private final SqsTemplate sqsTemplate;
    private final Mapping mapping;

    @Override
    public void send(Message<String> message) {
        try {
            DomainEvent domainEvent = serializer.deserialize(message.getPayload(), DomainEvent.class);
            String sqsName = domainEvent.getType();

            sqsTemplate.send(mapping.get(sqsName), message);
        } catch (JsonProcessingException e) {
            log.error("DomainEvent Deserialize Fail.");
        }
    }
}