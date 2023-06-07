package com.ys.order.domain.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Slf4j
public class DomainEvent<T> {

    private String type;
    private String occurredAt;
    private T payload;

    public DomainEvent(String type, T payload) {
        this.type = type;
        this.occurredAt = LocalDateTime.now().toString();
        this.payload = payload;
    }
}
