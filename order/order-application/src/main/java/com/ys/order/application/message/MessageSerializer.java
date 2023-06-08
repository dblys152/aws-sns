package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.order.domain.event.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public class MessageSerializer<T extends DomainEvent> implements Serializer<T> {

    private final ObjectMapper objectMapper;
    private final TypeReference<T> typeReference;

    public MessageSerializer() {
        this.objectMapper = new ObjectMapper();
        this.typeReference = new TypeReference<T>() {};
    }

    @Override
    public String serialize(T object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

    @Override
    public T deserialize(String payload, Class<T> payloadType) throws JsonProcessingException {
        return this.objectMapper.readValue(payload, payloadType);
    }
}
