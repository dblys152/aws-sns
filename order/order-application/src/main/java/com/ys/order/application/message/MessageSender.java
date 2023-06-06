package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.order.domain.event.DomainEvent;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageSender {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final Mapping mapping;

    public void sendSqs(MessageEnvelop<String> message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DomainEvent domainEvent = objectMapper.readValue(message.getPayload(), DomainEvent.class);
            String sqsName = domainEvent.getType();

            queueMessagingTemplate.convertAndSend(mapping.get(sqsName), message);
        } catch (JsonProcessingException e) {
            log.error("DomainEvent Deserialize Fail.");
        }
    }
}