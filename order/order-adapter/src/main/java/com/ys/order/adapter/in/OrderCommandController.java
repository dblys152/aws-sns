package com.ys.order.adapter.in;

import com.ys.order.adapter.in.model.ApiResponse;
import com.ys.order.adapter.in.model.OrderModel;
import com.ys.order.application.port.in.CompleteOrderCommand;
import com.ys.order.application.port.in.CompleteOrderUseCase;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderCommandController {

    private final CompleteOrderUseCase completeOrderUseCase;

    @PatchMapping("/{orderId}/completion")
    public ResponseEntity completeOrder(
            @PathVariable("orderId") String orderId) {

        Order ordered = completeOrderUseCase.complete(new CompleteOrderCommand(
                OrderId.of(orderId)));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(OrderModel.fromDomain(ordered)));
    }
}
