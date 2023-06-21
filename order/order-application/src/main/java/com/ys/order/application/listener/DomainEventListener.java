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

    private final MessageSender<DomainEvent> sender;

    @TransactionalEventListener
    public void on(DomainEvent event) {
        Message<DomainEvent> message = new GeneralMessage<>(event.serializePayload());
        sender.send(message);
        log.info("DomainEvent Receive name: {} OccurredAt: {}", event.getClass().getName(), event.getOccurredAt());
    }
}
