package com.ys.order.application.message;

import com.ys.infra.message.DomainEvent;
import com.ys.infra.message.GeneralMessage;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import com.ys.order.domain.event.OrderCompletedEvent;
import com.ys.refs.user.domain.UserId;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SqsSenderTest {

    private static final String ANY_SQS_NAME = "ANY_SQS_NAME";

    @InjectMocks
    private SqsSender sut;

    @Mock
    private Mapping mapping;
    @Mock
    private SqsTemplate sqsTemplate;

    private DomainEvent domainEvent;

    @BeforeEach
    void setUp()  {
        Order order = Order.of(OrderId.of("test"), UserId.of("testUserId"), LocalDateTime.now());
        OrderCompletedEvent event = OrderCompletedEvent.fromDomain(order);
        domainEvent = new DomainEvent<>(
                OrderCompletedEvent.class.getName(),
                event
        );
    }

    @Test
    void SQS_전송() {
        Message<DomainEvent> message = new GeneralMessage<>(domainEvent);
        given(mapping.get(any())).willReturn(ANY_SQS_NAME);

        sut.send(message);

        verify(sqsTemplate).send(ANY_SQS_NAME, message);
    }
}