package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Serializer<T> {

    String serialize(T object) throws JsonProcessingException;
    T deserialize(String payload, Class<T> payloadType) throws JsonProcessingException;
}
