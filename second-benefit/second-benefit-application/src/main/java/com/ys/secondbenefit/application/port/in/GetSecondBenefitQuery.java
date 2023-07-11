package com.ys.secondbenefit.application.port.in;

import com.ys.secondbenefit.domain.SecondBenefits;

public interface GetSecondBenefitQuery {

    SecondBenefits getAllByParams(GetSecondBenefitParams params);
}
