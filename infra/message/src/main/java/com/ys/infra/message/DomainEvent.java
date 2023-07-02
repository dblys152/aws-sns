package com.ys.infra.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DomainEvent<T> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final String type;
    private final String occurredAt;
    private final T payload;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DomainEvent(
            @JsonProperty("type") String type,
            @JsonProperty("occurredAt") String occurredAt,
            @JsonProperty("payload") T payload) {
        this.type = type;
        this.occurredAt = occurredAt;
        this.payload = payload;
    }

    public DomainEvent(String type, T payload) {
        this.type = type;
        this.occurredAt = LocalDateTime.now().toString();
        this.payload = payload;
    }

    public String serialize() {
        try {
            DomainEvent<String> domainEvent = this.serializePayload();
            return objectMapper.writeValueAsString(domainEvent);
        } catch (JsonProcessingException e) {
            throw new DomainEventException(this.type, e);
        }
    }

    public DomainEvent<String> serializePayload() {
        try {
            return new DomainEvent<>(
                    this.type,
                    this.occurredAt,
                    objectMapper.writeValueAsString(this.payload)
            );
        } catch (JsonProcessingException e) {
            throw new DomainEventException(this.type, e);
        }
    }

    public static <T> DomainEvent<T> deserialize(String serializedDomainEvent, Class<T> payloadType) {
        try {
            JavaType valueType = objectMapper.getTypeFactory().constructParametricType(DomainEvent.class, payloadType);
            return objectMapper.readValue(serializedDomainEvent, valueType);
        } catch (JsonProcessingException e) {
            throw new DomainEventException(serializedDomainEvent, e);
        }
    }

    public static <T> DomainEvent<T> deserializePayload(DomainEvent<String> serializedEvent, Class<T> payloadType) {
        try {
            T deserializedPayload = objectMapper.readValue(serializedEvent.getPayload(), payloadType);
            return new DomainEvent<>(
                    serializedEvent.getType(),
                    serializedEvent.getOccurredAt(),
                    deserializedPayload
            );
        } catch (JsonProcessingException e) {
            throw new DomainEventException(serializedEvent.getType(), e);
        }
    }
}
