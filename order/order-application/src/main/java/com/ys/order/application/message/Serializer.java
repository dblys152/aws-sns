package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Serializer<T> {

    public String serialize(T object) throws JsonProcessingException;
    public T deserialize(String payload, Class<T> payloadType) throws JsonProcessingException;
}
