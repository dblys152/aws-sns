package com.ys.order.domain.event;

import com.ys.order.domain.core.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCompletedEvent {

    String orderId;

    public OrderCompletedEvent(String orderId) {
        this.orderId = orderId;
    }

    public static OrderCompletedEvent fromDomain(Order order) {
        return new OrderCompletedEvent(order.getId().getId());
    }
}
