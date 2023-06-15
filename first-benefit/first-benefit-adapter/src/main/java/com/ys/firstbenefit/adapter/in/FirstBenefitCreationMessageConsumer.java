package com.ys.firstbenefit.adapter.in;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirstBenefitCreationMessageConsumer {

    @SqsListener(value = "${aws.sqs.FIRST_BENEFIT_CREATION_QUEUE}", factory = "defaultSqsListenerContainerFactory")
    public void receive(String message, Acknowledgement ack) {
        log.info("{}", message);
    }
}
