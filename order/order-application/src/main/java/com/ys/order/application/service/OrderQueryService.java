package com.ys.order.application.service;

import com.ys.order.application.port.in.GetOrderQuery;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import com.ys.refs.user.domain.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class OrderQueryService implements GetOrderQuery {

    @Override
    public Order get(OrderId orderId) {
        return Order.of(
                orderId,
                UserId.of("testUserId"),
                LocalDateTime.now()
        );
    }
}
