package com.ys.secondbenefit.application.service;

import com.ys.infra.utils.CommandFactory;
import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.application.event.OrderCompletedEvent;
import com.ys.secondbenefit.application.port.in.CreateSecondBenefitUseCase;
import com.ys.secondbenefit.domain.CreateSecondBenefitCommand;
import com.ys.secondbenefit.domain.SecondBenefitType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderCompletedEventProcessorTest {

    private static final String ANY_ORDER_ID = "ANY_ORDER_ID";
    private static final UserId ANY_USER_ID = UserId.of("testUserId");

    @InjectMocks
    private OrderCompletedEventProcessor sut;

    @Mock
    private CommandFactory<String, CreateSecondBenefitCommand> commandFactory;
    @Mock
    private CreateSecondBenefitUseCase createSecondBenefitUseCase;

    @Test
    void accept() {
        OrderCompletedEvent event = new OrderCompletedEvent(ANY_ORDER_ID);
        CreateSecondBenefitCommand command = new CreateSecondBenefitCommand(ANY_USER_ID, ANY_ORDER_ID, SecondBenefitType.ORDER);
        given(commandFactory.create(ANY_ORDER_ID)).willReturn(command);

        sut.accept(event);

        verify(createSecondBenefitUseCase).create(command);
    }
}