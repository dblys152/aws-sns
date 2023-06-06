package com.ys.order.domain.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@Slf4j
public class DomainEvent<T> {

    private final String type;
    private final String occurredAt;
    private final T payload;

    public DomainEvent(String type, T payload) {
        this.type = type;
        this.occurredAt = LocalDateTime.now().toString();
        this.payload = payload;
    }

    public String serialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
