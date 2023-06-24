package com.ys.secondbenefit.application.service;

import com.ys.secondbenefit.application.port.in.CreateSecondBenefitCommand;
import com.ys.secondbenefit.application.port.in.CreateSecondBenefitUseCase;
import com.ys.secondbenefit.application.port.out.RecordSecondBenefitPort;
import com.ys.secondbenefit.domain.SecondBenefit;
import com.ys.secondbenefit.domain.SecondBenefitType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SecondBenefitCommandService implements CreateSecondBenefitUseCase {

    private final RecordSecondBenefitPort recordSecondBenefitPort;

    @Override
    public SecondBenefit create(CreateSecondBenefitCommand command) {
        SecondBenefitType type = command.getType();
        SecondBenefit createdFirstBenefit = SecondBenefit.create(command.getUserId(), type);
        createdFirstBenefit.createTargetMapping(command.getTargetId());

        SecondBenefit savedFirstBenefit = recordSecondBenefitPort.save(createdFirstBenefit);

        return savedFirstBenefit;
    }
}
