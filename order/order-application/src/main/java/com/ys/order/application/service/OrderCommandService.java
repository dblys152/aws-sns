package com.ys.order.application.service;

import com.ys.order.application.port.in.CompleteOrderCommand;
import com.ys.order.application.port.in.CompleteOrderUseCase;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.event.DomainEvent;
import com.ys.order.domain.event.OrderCompletedEvent;
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
                "testUserId",
                LocalDateTime.now()
        );

        OrderCompletedEvent event = OrderCompletedEvent.fromDomain(completedOrder);
        DomainEvent<OrderCompletedEvent> domainEvent = new DomainEvent(
                OrderCompletedEvent.class.getName(),
                event
        );
        eventPublisher.publishEvent(domainEvent);

        return completedOrder;
    }
}
