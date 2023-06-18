package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import com.ys.order.domain.event.OrderCompletedEvent;
import com.ys.refs.user.domain.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class MessageSerializerTest {

    private MessageSerializer<DomainEvent> sut;
    private ObjectMapper objectMapper;

    private DomainEvent<OrderCompletedEvent> domainEvent;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        sut = new MessageSerializer<>(objectMapper);

        Order order = Order.of(OrderId.of("test"), UserId.of("testUserId"), LocalDateTime.now());
        OrderCompletedEvent event = OrderCompletedEvent.fromDomain(order);
        domainEvent = new DomainEvent<>(
                OrderCompletedEvent.class.getName(),
                event
        );
    }

    @Test
    void serialize() throws JsonProcessingException {
        String actual = sut.serialize(domainEvent);

        assertThat(actual).isNotEmpty();
    }

    @Test
    void deserialize() throws JsonProcessingException {
        String payload = sut.serialize(domainEvent);

        DomainEvent actual = sut.deserialize(payload, DomainEvent.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getType()).isEqualTo(OrderCompletedEvent.class.getName());
        assertThat(actual.getPayload()).isInstanceOf(LinkedHashMap.class);
    }
}