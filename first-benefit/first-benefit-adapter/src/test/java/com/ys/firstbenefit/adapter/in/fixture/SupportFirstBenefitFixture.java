package com.ys.firstbenefit.adapter.in.fixture;

import com.ys.firstbenefit.domain.*;
import com.ys.refs.user.domain.UserId;

import java.time.LocalDateTime;

public class SupportFirstBenefitFixture {

    protected static final LocalDateTime NOW = LocalDateTime.now();
    protected static final FirstBenefitId ANY_FIRST_BENEFIT_ID = FirstBenefitId.of("ANY_FIRST_BENEFIT_ID");
    protected static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");
    protected static final LocalDateTime ANY_EXPIRED_AT = LocalDateTime.now().plusDays(30);
    protected static final String ANY_ORDER_ID = "ANY_ORDER_ID";
    protected static final FirstBenefit ANY_FIRST_BENEFIT = FirstBenefit.of(
            ANY_FIRST_BENEFIT_ID,
            ANY_USER_ID,
            FirstBenefitType.ORDER,
            FirstBenefitStatus.AVAILABLE,
            FirstBenefitExpiredAt.of(ANY_EXPIRED_AT),
            FirstBenefitTargetMapping.of(ANY_FIRST_BENEFIT_ID, ANY_ORDER_ID, 0L),
            NOW, NOW, 0L
    );
}
