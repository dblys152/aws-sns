package com.ys.order.application.listener;

import com.ys.infra.message.DomainEvent;
import com.ys.order.application.message.SnsSender;
import com.ys.order.application.message.SqsSender;
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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DomainEventListenerTest {

    private static final String ANY_ORDER_ID = "ANY_ORDER_ID";

    @InjectMocks
    private DomainEventListener sut;

    @Mock
    private SnsSender snsSender;
    @Mock
    private SqsSender sqsSender;

    @Captor
    private ArgumentCaptor<Message<String>> snsCaptor;
    @Captor
    private ArgumentCaptor<Message<DomainEvent>> sqsCaptor;

    private DomainEvent domainEvent;

    @BeforeEach
    void setUp() {
        domainEvent = DomainEvent.of(
                OrderCompletedEvent.class.getName(),
                new OrderCompletedEvent(ANY_ORDER_ID)
        );
    }

    @Test
    void 수신된_도메인_이벤트_메시지_전송() {
        sut.on(domainEvent);

        verify(snsSender).send(snsCaptor.capture());
        Message<String> snsMessage = snsCaptor.getValue();
        verify(sqsSender).send(sqsCaptor.capture());
        Message<DomainEvent> sqsMessage = sqsCaptor.getValue();
        assertAll(
                () -> assertThat(snsMessage.getHeaders()).isNotEmpty(),
                () -> assertThat(snsMessage.getPayload()).isNotEmpty(),
                () -> assertThat(sqsMessage.getHeaders()).isNotEmpty(),
                () -> assertThat(sqsMessage.getPayload()).isNotNull()
        );
    }
}