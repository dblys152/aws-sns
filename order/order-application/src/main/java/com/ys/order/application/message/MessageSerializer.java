package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSerializer<T extends DomainEvent> implements Serializer<T> {

    private final ObjectMapper objectMapper;

    @Override
    public String serialize(T object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

    @Override
    public T deserialize(String payload, Class<T> payloadType) throws JsonProcessingException {
        return this.objectMapper.readValue(payload, payloadType);
    }
}
