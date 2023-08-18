package com.ys.order.adapter.in;

import com.ys.infra.utils.ApiResponse;
import com.ys.order.adapter.in.model.OrderModel;
import com.ys.order.application.port.in.GetOrderQuery;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/orders",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderQueryController {

    private final GetOrderQuery getOrderQuery;

    @GetMapping("/{orderId}")
    public ResponseEntity getOrder(
            @PathVariable("orderId") String orderId) {

        Order order = getOrderQuery.get(OrderId.of(orderId));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(HttpStatus.OK.value(), OrderModel.fromDomain(order)));
    }
}
