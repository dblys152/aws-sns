package com.ys.order.application.service;

import com.ys.infra.message.DomainEvent;
import com.ys.order.application.port.in.CompleteOrderCommand;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import com.ys.order.domain.event.OrderCompletedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderCommandServiceTest {

    private static final OrderId ANY_ORDER_ID = OrderId.of("ANY_ORDER_ID");

    @InjectMocks
    private OrderCommandService sut;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Captor
    private ArgumentCaptor<DomainEvent> captor;

    @Test
    void 주문_완료_후_도메인_이벤트_발행() {
        CompleteOrderCommand command = new CompleteOrderCommand(ANY_ORDER_ID);

        Order actual = sut.complete(command);

        verify(eventPublisher).publishEvent(captor.capture());
        DomainEvent<OrderCompletedEvent> publishedEvent = captor.getValue();
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(publishedEvent).isNotNull(),
                () -> assertThat(publishedEvent.getType()).isEqualTo(OrderCompletedEvent.class.getName()),
                () -> assertThat(actual.getId().getId()).isEqualTo(publishedEvent.getPayload().getOrderId())
        );
    }
}