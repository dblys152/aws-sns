package com.ys.order.application.message;

import com.ys.infra.message.DomainEvent;
import com.ys.infra.message.GeneralMessage;
import com.ys.order.domain.event.OrderCompletedEvent;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SnsSenderTest {

    private static final String ANY_ORDER_ID = "ANY_ORDER_ID";

    private static final String ANY_SNS_NAME = "ANY_SNS_NAME";

    @InjectMocks
    private SnsSender sut;

    @Mock
    private SnsMapping mapping;
    @Mock
    private SnsTemplate snsTemplate;

    private String serializedDomainEvent;

    @BeforeEach
    void setUp()  {
        serializedDomainEvent = DomainEvent.of(
                OrderCompletedEvent.class.getName(),
                new OrderCompletedEvent(ANY_ORDER_ID)
        ).serialize();
    }

    @Test
    void SNS_메지시_전송() {
        Message<String> message = new GeneralMessage<>(serializedDomainEvent);
        given(mapping.get(any())).willReturn(ANY_SNS_NAME);

        sut.send(message);

        verify(snsTemplate).send(ANY_SNS_NAME, message);
    }
}