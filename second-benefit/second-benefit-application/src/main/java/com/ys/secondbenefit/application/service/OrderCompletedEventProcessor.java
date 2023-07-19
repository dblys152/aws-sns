package com.ys.secondbenefit.application.service;

import com.ys.secondbenefit.application.event.OrderCompletedEvent;
import com.ys.secondbenefit.domain.CreateSecondBenefitCommand;
import com.ys.secondbenefit.application.port.in.CreateSecondBenefitUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class OrderCompletedEventProcessor implements Consumer<OrderCompletedEvent> {

    private final CommandFactory<String, CreateSecondBenefitCommand> commandFactory;
    private final CreateSecondBenefitUseCase createSecondBenefitUseCase;

    @Override
    public void accept(OrderCompletedEvent event) {
        String orderId = event.getOrderId();
        createSecondBenefitUseCase.create(commandFactory.create(orderId));
    }
}
