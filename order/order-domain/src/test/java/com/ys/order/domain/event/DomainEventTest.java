package com.ys.order.domain.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DomainEventTest {

    @Test
    void serialize() throws JsonProcessingException {
        OrderCompletedEvent orderCompletedEvent = OrderCompletedEvent.fromDomain(
                Order.of(OrderId.of("test"), "testUserId", LocalDateTime.now()));
        DomainEvent<OrderCompletedEvent> domainEvent = new DomainEvent(orderCompletedEvent.getClass().getName(), orderCompletedEvent);
        ObjectMapper objectMapper = new ObjectMapper();

        String actual = objectMapper.writeValueAsString(domainEvent);

        assertThat(actual).isNotEmpty();
    }
}