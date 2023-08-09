package com.ys.order.application.service;

import com.ys.infra.message.DomainEvent;
import com.ys.order.application.port.in.CompleteOrderCommand;
import com.ys.order.application.port.in.CompleteOrderUseCase;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.event.OrderCompletedEvent;
import com.ys.refs.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderCommandService implements CompleteOrderUseCase {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Order complete(CompleteOrderCommand command) {

        Order completedOrder = Order.of(
                command.getOrderId(),
                UserId.of("testUserId"),
                LocalDateTime.now()
        );

        eventPublisher.publishEvent(DomainEvent.of(
                OrderCompletedEvent.class.getName(),
                OrderCompletedEvent.fromDomain(completedOrder)
        ));

        return completedOrder;
    }
}
