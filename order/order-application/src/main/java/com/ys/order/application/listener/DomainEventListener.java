package com.ys.order.application.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ys.order.application.message.*;
import com.ys.order.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class DomainEventListener {

    private final Serializer<DomainEvent> serializer;
    private final MessageSender sender;

    @TransactionalEventListener
    public void on(DomainEvent event)  {
        try {
            MessageEnvelop<String> messageEnvelop = new GeneralMessageEnvelop<>(serializer.serialize(event));
            sender.sendSqs(messageEnvelop);
            log.info("DomainEvent Receive name: {} OccurredAt: {}", event.getClass().getName(), event.getOccurredAt());
        } catch (JsonProcessingException e) {
            log.error("DomainEvent Serialize Fail.");
        }
    }
}
