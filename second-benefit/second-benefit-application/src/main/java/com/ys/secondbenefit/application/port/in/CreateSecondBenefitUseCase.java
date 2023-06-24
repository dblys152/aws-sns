package com.ys.secondbenefit.application.port.in;

import com.ys.secondbenefit.domain.SecondBenefit;

public interface CreateSecondBenefitUseCase {

    SecondBenefit create(CreateSecondBenefitCommand command);
}
