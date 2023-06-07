package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import com.ys.order.domain.event.DomainEvent;
import com.ys.order.domain.event.OrderCompletedEvent;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageSenderTest {

    @InjectMocks
    private MessageSender sut;

    @Mock
    private Serializer<DomainEvent> serializer;
    @Mock
    private Mapping mapping;
    @Mock
    private QueueMessagingTemplate queueMessagingTemplate;

    private MessageEnvelop<String> message;
    private DomainEvent domainEvent;

    @BeforeEach
    void setUp()  {
        Order order = Order.of(OrderId.of("test"), "testUserId", LocalDateTime.now());
        OrderCompletedEvent event = OrderCompletedEvent.fromDomain(order);
        domainEvent = new DomainEvent<OrderCompletedEvent>(
                OrderCompletedEvent.class.getName(),
                event
        );
    }

    @Test
    void SQS_전송() throws JsonProcessingException {
        message = new GeneralMessageEnvelop<>(serializer.serialize(domainEvent));
        given(serializer.deserialize(message.getPayload(), DomainEvent.class)).willReturn(domainEvent);
        given(mapping.get(domainEvent.getType())).willReturn("mockQueueName");

        sut.sendSqs(message);

        verify(queueMessagingTemplate).convertAndSend("mockQueueName", message);
    }
}