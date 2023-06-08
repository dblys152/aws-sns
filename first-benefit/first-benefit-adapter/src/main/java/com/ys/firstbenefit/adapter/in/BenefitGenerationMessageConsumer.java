package com.ys.firstbenefit.adapter.in;

import com.ys.firstbenefit.application.message.GeneralMessageEnvelop;
import io.awspring.cloud.messaging.listener.Acknowledgment;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy.NEVER;

@Component
@Slf4j
public class BenefitGenerationMessageConsumer {

    @SqsListener(value = "FIRST_BENEFIT_GENERATION_QUEUE", deletionPolicy = NEVER)
    public void receive(String messageEnvelope, Acknowledgment acks) {
        log.info("{}", messageEnvelope);
//        GeneralMessageEnvelopProcessReturn processReturn = template.doProcess(messageEnvelope, SignupSubscriptionEventV003.class, processor);
//        switch (processReturn) {
//            case IGNORE:
//            case SUCCESS:
//                acks.acknowledge();
//                break;
//            case RETRY:
//                break;
//        }
    }
}
