package com.ys.firstbenefit.application.service;

import com.ys.firstbenefit.application.port.out.LoadOrderWebPort;
import com.ys.firstbenefit.application.port.out.ResponseModel;
import com.ys.firstbenefit.domain.CreateFirstBenefitCommand;
import com.ys.firstbenefit.domain.FirstBenefitType;
import com.ys.infra.utils.CommandFactory;
import com.ys.refs.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateFirstBenefitByOrderCommandFactory implements CommandFactory<String, CreateFirstBenefitCommand> {

    private static final FirstBenefitType ORDER_TYPE = FirstBenefitType.ORDER;

    private final LoadOrderWebPort loadOrderWebPort;

    @Override
    public CreateFirstBenefitCommand create(String orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("orderId가 null 입니다.");
        }

        ResponseModel.OrderModel model = loadOrderWebPort.get(orderId);

        return new CreateFirstBenefitCommand(
                UserId.of(model.getOrdererUserId()),
                model.getId(),
                ORDER_TYPE
        );
    }
}
