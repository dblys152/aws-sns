package com.ys.secondbenefit.application.service;

import com.ys.secondbenefit.application.port.in.CreateSecondBenefitUseCase;
import com.ys.secondbenefit.application.port.out.RecordSecondBenefitPort;
import com.ys.secondbenefit.domain.CreateSecondBenefitCommand;
import com.ys.secondbenefit.domain.SecondBenefit;
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
        SecondBenefit createdSecondBenefit = SecondBenefit.create(command);

        SecondBenefit savedSecondBenefit = recordSecondBenefitPort.save(createdSecondBenefit);

        return savedSecondBenefit;
    }
}
