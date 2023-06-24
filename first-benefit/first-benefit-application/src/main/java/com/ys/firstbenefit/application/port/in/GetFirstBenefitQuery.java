package com.ys.firstbenefit.application.port.in;

import com.ys.firstbenefit.domain.FirstBenefits;
import com.ys.refs.user.domain.UserId;

public interface GetFirstBenefitQuery {

    FirstBenefits getAllByUserId(UserId userId);
}
