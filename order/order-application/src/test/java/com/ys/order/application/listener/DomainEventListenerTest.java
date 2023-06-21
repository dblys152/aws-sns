package com.ys.order.application.listener;

import com.ys.infra.message.DomainEvent;
import com.ys.order.application.message.MessageSender;
import com.ys.order.domain.event.OrderCompletedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DomainEventListenerTest {

    private static final String ANY_ORDER_ID = "ANY_ORDER_ID";

    @InjectMocks
    private DomainEventListener sut;

    @Mock
    private MessageSender<String> sender;

    @Captor
    private ArgumentCaptor<Message<String>> captor;

    private DomainEvent<OrderCompletedEvent> domainEvent;

    @BeforeEach
    void setUp() {
        domainEvent = new DomainEvent<>(
                OrderCompletedEvent.class.getName(),
                new OrderCompletedEvent(ANY_ORDER_ID)
        );
    }

    @Test
    void 수신된_도메인_이벤트_메시지_전송() {
        sut.on(domainEvent);

        verify(sender).send(captor.capture());
        Message<String> message = captor.getValue();
        assertThat(message.getHeaders()).isNotEmpty();
        assertThat(message.getPayload()).isNotEmpty();
    }
}