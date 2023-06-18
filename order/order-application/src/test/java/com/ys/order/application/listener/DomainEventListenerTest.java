package com.ys.order.application.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.order.application.message.DomainEvent;
import com.ys.order.application.message.MessageSender;
import com.ys.order.application.message.Serializer;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DomainEventListenerTest {

    private static final String ANY_ORDER_ID = "ANY_ORDER_ID";

    @InjectMocks
    private DomainEventListener sut;

    @Mock
    private Serializer<DomainEvent> serializer;
    @Mock
    private MessageSender<String> sender;

    @Captor
    private ArgumentCaptor<Message<String>> captor;

    private DomainEvent<OrderCompletedEvent> domainEvent;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        domainEvent = new DomainEvent<>(
                OrderCompletedEvent.class.getName(),
                new OrderCompletedEvent(ANY_ORDER_ID)
        );
        objectMapper = new ObjectMapper();
    }

    @Test
    void 수신된_도메인_이벤트_메시지_전송() throws JsonProcessingException {
        String serializedEvent = objectMapper.writeValueAsString(domainEvent);
        given(serializer.serialize(any(DomainEvent.class))).willReturn(serializedEvent);

        sut.on(domainEvent);

        verify(sender).send(captor.capture());
        assertThat(captor.getValue().getPayload()).isEqualTo(serializedEvent);
    }

    @Test
    void 수신된_도메인_이벤트_Serialize_실패() throws JsonProcessingException {
        given(serializer.serialize(any(DomainEvent.class))).willThrow(JsonProcessingException.class);

        assertThatThrownBy(() -> sut.on(domainEvent)).isInstanceOf(JsonProcessingException.class);
    }
}