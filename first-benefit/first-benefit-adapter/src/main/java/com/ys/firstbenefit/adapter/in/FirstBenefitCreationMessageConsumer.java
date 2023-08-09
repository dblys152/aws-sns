package com.ys.firstbenefit.adapter.in;

import com.ys.firstbenefit.application.event.OrderCompletedEvent;
import com.ys.firstbenefit.application.service.OrderCompletedEventProcessor;
import com.ys.infra.message.DomainEvent;
import com.ys.infra.message.GeneralMessageProcessReturn;
import com.ys.infra.message.GeneralMessageProcessTemplate;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirstBenefitCreationMessageConsumer {

    @Setter(value = AccessLevel.PACKAGE)
    private GeneralMessageProcessTemplate template = new GeneralMessageProcessTemplate();
    private final OrderCompletedEventProcessor processor;

    @SqsListener(value = "${aws.sqs.FIRST_BENEFIT_CREATION_QUEUE}", factory = "sqsListenerContainerFactory")
    public void receive(Message<DomainEvent> message, Acknowledgement ack) throws IOException {
        GeneralMessageProcessReturn processReturn = template.doProcess(message, OrderCompletedEvent.class, processor);
        switch (processReturn) {
            case IGNORE, SUCCESS -> {
                ack.acknowledge();
            }
        }
    }
}
