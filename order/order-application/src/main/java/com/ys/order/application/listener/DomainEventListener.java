package com.ys.order.application.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ys.order.application.message.GeneralMessageEnvelop;
import com.ys.order.application.message.MessageEnvelop;
import com.ys.order.application.message.MessageSender;
import com.ys.order.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class DomainEventListener {

    private final MessageSender sender;

    @TransactionalEventListener
    public void on(DomainEvent event)  {
        try {
            MessageEnvelop<String> messageEnvelop = new GeneralMessageEnvelop<>(event.serialize());
            sender.sendSqs(messageEnvelop);
            log.info("DomainEvent Receive name: {} OccurredAt: {}", event.getClass().getName(), event.getOccurredAt());
        } catch (JsonProcessingException e) {
            log.error("DomainEvent Serialize Fail.");
        }
    }
}
