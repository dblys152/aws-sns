package com.ys.firstbenefit.application.service;

import com.ys.firstbenefit.application.port.in.CreateFirstBenefitUseCase;
import com.ys.firstbenefit.application.port.out.RecordFirstBenefitPort;
import com.ys.firstbenefit.domain.CreateFirstBenefitCommand;
import com.ys.firstbenefit.domain.FirstBenefit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FirstBenefitCommandService implements CreateFirstBenefitUseCase {

    private final RecordFirstBenefitPort recordFirstBenefitPort;

    @Override
    public FirstBenefit create(CreateFirstBenefitCommand command) {
        FirstBenefit createdFirstBenefit = FirstBenefit.create(command);

        FirstBenefit savedFirstBenefit = recordFirstBenefitPort.save(createdFirstBenefit);

        return savedFirstBenefit;
    }
}
