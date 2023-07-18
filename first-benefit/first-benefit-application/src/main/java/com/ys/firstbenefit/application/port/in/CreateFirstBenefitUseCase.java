package com.ys.firstbenefit.application.port.in;

import com.ys.firstbenefit.domain.CreateFirstBenefitCommand;
import com.ys.firstbenefit.domain.FirstBenefit;

public interface CreateFirstBenefitUseCase {

    FirstBenefit create(CreateFirstBenefitCommand command);
}
