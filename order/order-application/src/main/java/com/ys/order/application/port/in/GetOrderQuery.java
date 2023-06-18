package com.ys.order.application.port.in;

import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;

public interface GetOrderQuery {

    Order get(OrderId orderId);
}
