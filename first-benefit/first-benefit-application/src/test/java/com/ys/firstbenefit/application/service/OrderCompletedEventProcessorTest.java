package com.ys.firstbenefit.application.service;

import com.ys.firstbenefit.application.event.OrderCompletedEvent;
import com.ys.firstbenefit.application.port.in.CreateFirstBenefitUseCase;
import com.ys.firstbenefit.domain.CreateFirstBenefitCommand;
import com.ys.firstbenefit.domain.FirstBenefitType;
import com.ys.infra.utils.CommandFactory;
import com.ys.refs.user.domain.UserId;
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
    private CommandFactory<String, CreateFirstBenefitCommand> commandFactory;
    @Mock
    private CreateFirstBenefitUseCase createFirstBenefitUseCase;

    @Test
    void accept() {
        OrderCompletedEvent event = new OrderCompletedEvent(ANY_ORDER_ID);
        CreateFirstBenefitCommand command = new CreateFirstBenefitCommand(ANY_USER_ID, ANY_ORDER_ID, FirstBenefitType.ORDER);
        given(commandFactory.create(ANY_ORDER_ID)).willReturn(command);

        sut.accept(event);

        verify(createFirstBenefitUseCase).create(command);
    }
}