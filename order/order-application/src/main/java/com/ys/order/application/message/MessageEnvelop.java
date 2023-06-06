package com.ys.order.application.message;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.NotNull;

public interface MessageEnvelop<T> {

    @NotNull
    String getMessageId();

    @NotNull
    T getPayload();
}
