package com.ys.order.application.message;

import com.fasterxml.uuid.Generators;
import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.HashMap;
import java.util.Map;

@Data
public class GeneralMessage<T> implements Message<T> {

    private T payload;
    private MessageHeaders headers;

    public GeneralMessage(T payload) {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("contentType", "application/json");
        this.payload = payload;
        this.headers = new MessageHeaders(headerMap);
    }

    @Override
    public T getPayload() {
        return this.payload;
    }

    @Override
    public MessageHeaders getHeaders() {
        return this.headers;
    }
}
