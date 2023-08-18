package com.ys.infra.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

import static com.ys.infra.message.GeneralMessageProcessReturn.*;

@Slf4j
public class GeneralMessageProcessTemplate {

    private final EventValidator eventValidator;

    public GeneralMessageProcessTemplate() {
        this.eventValidator = new EventValidator();
    }

    public <T> GeneralMessageProcessReturn doProcess(Message<DomainEvent> message, Class<T> aEventClass, Consumer<T> processor) {
        if (message == null || message.getPayload() == null) {
            log.error("Message or Message payload is null");
            return IGNORE;
        }
        DomainEvent<T> domainEvent = DomainEvent.deserializePayload(message.getPayload(), aEventClass);
        String messageId = domainEvent.getId();

        if (aEventClass == null) {
            log.error(String.format("aEventClass is null. MessageId is %s, Message Payload is %s", messageId, domainEvent));
            return RETRY;
        }

        if (processor == null) {
            log.error(String.format("processor is null. MessageId is %s, Message Payload is %s", messageId, domainEvent));
            return RETRY;
        }

        try {
            log.info(String.format("Received a message. EventType is %s, OccurredAt is %s",
                    domainEvent.getType(), domainEvent.getOccurredAt()));

            T event = domainEvent.getPayload();

            eventValidator.validateAndThrow(event);

            processor.accept(event);
            return SUCCESS;
        } catch (EventValidateException e) {
            log.error(String.format("%s has violationMessages. Messages: '%s'", e.getEventType(), e.getViolationMessages()), e);
            return IGNORE;
        } catch (Exception e) {
            log.error(String.format("Failed to process of Message. MessageId is %s, Exception Message : %s", messageId, e.getMessage()));
            e.printStackTrace();
            return RETRY;
        }
    }
}
