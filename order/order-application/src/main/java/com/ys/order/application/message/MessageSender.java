package com.ys.order.application.message;

import org.springframework.messaging.Message;

public interface MessageSender<T> {

    void send(Message<T> message);
}