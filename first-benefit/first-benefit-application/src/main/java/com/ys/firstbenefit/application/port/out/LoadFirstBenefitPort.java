package com.ys.firstbenefit.application.port.out;

import com.ys.firstbenefit.domain.FirstBenefits;
import com.ys.refs.user.domain.UserId;

public interface LoadFirstBenefitPort {

    FirstBenefits findAllByUserId(UserId userId);
}
