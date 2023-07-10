package com.ys.firstbenefit.application.port.out;

import com.ys.firstbenefit.application.port.in.GetFirstBenefitParams;
import com.ys.firstbenefit.domain.FirstBenefits;

public interface LoadFirstBenefitPort {

    FirstBenefits findAllByParams(GetFirstBenefitParams params);
}
