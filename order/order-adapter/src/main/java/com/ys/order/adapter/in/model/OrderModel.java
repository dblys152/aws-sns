package com.ys.order.adapter.in.model;

import com.ys.order.domain.core.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderModel {

    String id;
    String ordererUserId;
    LocalDateTime completedAt;

    public static OrderModel fromDomain(Order order) {
        return new OrderModel(order.getId().getId(), order.getOrdererUserId().getId(), order.getCompletedAt());
    }
}
