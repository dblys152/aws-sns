package com.ys.order.application.message;

import com.ys.infra.message.DomainEvent;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqsSender implements MessageSender<DomainEvent> {

    private final SqsTemplate sqsTemplate;
    private final SqsSenderMapping mapping;

    @Override
    public void send(Message<DomainEvent> message) {
        String sqsName = message.getPayload().getType();

        sqsTemplate.send(mapping.get(sqsName), message);
    }
}