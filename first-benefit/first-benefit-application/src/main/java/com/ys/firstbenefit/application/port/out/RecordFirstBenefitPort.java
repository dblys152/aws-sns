package com.ys.firstbenefit.application.port.out;

import com.ys.firstbenefit.domain.FirstBenefit;

public interface RecordFirstBenefitPort {

    FirstBenefit save(FirstBenefit firstBenefit);
}
