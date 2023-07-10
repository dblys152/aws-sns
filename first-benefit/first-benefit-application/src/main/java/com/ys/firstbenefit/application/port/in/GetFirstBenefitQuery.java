package com.ys.firstbenefit.application.port.in;

import com.ys.firstbenefit.domain.FirstBenefits;

public interface GetFirstBenefitQuery {

    FirstBenefits getAllByParams(GetFirstBenefitParams params);
}
