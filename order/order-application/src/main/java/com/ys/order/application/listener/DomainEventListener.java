package com.ys.order.application.listener;

import com.ys.infra.message.DomainEvent;
import com.ys.infra.message.GeneralMessage;
import com.ys.order.application.message.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class DomainEventListener {

    private final SnsSender snsSender;
    private final SqsSender sqsSender;

    @TransactionalEventListener
    public void on(DomainEvent event) {
        Message<String> snsMessage = new GeneralMessage<>(event.serialize());
        snsSender.send(snsMessage);
        Message<DomainEvent> sqsMessage = new GeneralMessage<>(event.serializePayload());
        sqsSender.send(sqsMessage);
        log.info("Receive DomainEvent name: {} OccurredAt: {}", event.getType(), event.getOccurredAt());
    }
}
