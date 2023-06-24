package com.ys.secondbenefit.application.port.out;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.domain.SecondBenefits;

public interface LoadSecondBenefitPort {

    SecondBenefits findAllByUserId(UserId userId);
}
