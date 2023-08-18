package com.ys.secondbenefit.application.service;

import com.ys.infra.utils.CommandFactory;
import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.application.port.out.LoadOrderWebPort;
import com.ys.secondbenefit.application.port.out.ResponseModel;
import com.ys.secondbenefit.domain.CreateSecondBenefitCommand;
import com.ys.secondbenefit.domain.SecondBenefitType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateSecondBenefitByOrderCommandFactory implements CommandFactory<String, CreateSecondBenefitCommand> {

    private static final SecondBenefitType ORDER_TYPE = SecondBenefitType.ORDER;

    private final LoadOrderWebPort loadOrderWebPort;

    @Override
    public CreateSecondBenefitCommand create(String orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("orderId가 null 입니다.");
        }

        ResponseModel.OrderModel model = loadOrderWebPort.get(orderId);

        return new CreateSecondBenefitCommand(
                UserId.of(model.getOrdererUserId()),
                model.getId(),
                ORDER_TYPE
        );
    }
}
