package com.ys.order.application.message;

import com.ys.infra.message.DomainEvent;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SnsSender implements MessageSender<String> {

    private final SnsTemplate snsTemplate;
    private final SnsSenderMapping mapping;

    @Override
    public void send(Message<String> message) {
        String snsName = DomainEvent.deserialize(message.getPayload(), String.class).getType();

        snsTemplate.send(mapping.get(snsName), message);
    }
}