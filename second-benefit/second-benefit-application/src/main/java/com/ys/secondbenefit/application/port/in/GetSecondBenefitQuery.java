package com.ys.secondbenefit.application.port.in;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.domain.SecondBenefits;

public interface GetSecondBenefitQuery {

    SecondBenefits getAllByUserId(UserId userId);
}
