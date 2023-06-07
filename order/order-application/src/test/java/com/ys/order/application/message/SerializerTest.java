package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ys.order.application.message.Serializer;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import com.ys.order.domain.event.DomainEvent;
import com.ys.order.domain.event.OrderCompletedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class SerializerTest {

    private Serializer<DomainEvent> serializer;
    private DomainEvent domainEvent;

    @BeforeEach
    void setUp() {
        serializer = new Serializer<>();
        Order order = Order.of(OrderId.of("test"), "testUserId", LocalDateTime.now());
        OrderCompletedEvent event = OrderCompletedEvent.fromDomain(order);
        domainEvent = new DomainEvent<OrderCompletedEvent>(
                OrderCompletedEvent.class.getName(),
                event
        );
    }

    @Test
    void serialize() throws JsonProcessingException {
        String actual = serializer.serialize(domainEvent);

        assertThat(actual).isNotEmpty();
    }

    @Test
    void deserialize() throws JsonProcessingException {
        String payload = serializer.serialize(domainEvent);

        DomainEvent actual = (DomainEvent) serializer.deserialize(payload, DomainEvent.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getType()).isEqualTo(OrderCompletedEvent.class.getName());
        assertThat(actual.getPayload()).isInstanceOf(LinkedHashMap.class);
    }
}