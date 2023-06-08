package com.ys.firstbenefit.application.message;


import jakarta.validation.constraints.NotNull;

public interface MessageEnvelop<T> {

    @NotNull
    String getMessageId();

    @NotNull
    T getPayload();
}
