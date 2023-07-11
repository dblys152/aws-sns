package com.ys.secondbenefit.application.port.out;

import com.ys.secondbenefit.application.port.in.GetSecondBenefitParams;
import com.ys.secondbenefit.domain.SecondBenefits;

public interface LoadSecondBenefitPort {

    SecondBenefits findAllByParams(GetSecondBenefitParams params);
}
