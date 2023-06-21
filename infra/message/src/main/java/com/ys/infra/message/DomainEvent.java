package com.ys.infra.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class DomainEvent<T> {

    @JsonProperty("type")
    private final String type;
    @JsonProperty("occurredAt")
    private final String occurredAt;
    @JsonProperty("payload")
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

    public DomainEvent<String> serializePayload() {
        ObjectMapper objectMapper = new ObjectMapper();
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

    public static <T> DomainEvent<T> deserializePayload(DomainEvent<String> serializedEvent, Class<T> payloadType) {
        ObjectMapper objectMapper = new ObjectMapper();
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
