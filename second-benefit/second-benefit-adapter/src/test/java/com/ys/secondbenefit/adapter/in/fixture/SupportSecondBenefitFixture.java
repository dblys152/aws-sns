package com.ys.secondbenefit.adapter.in.fixture;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.domain.*;

import java.time.LocalDateTime;

public class SupportSecondBenefitFixture {

    protected static final LocalDateTime NOW = LocalDateTime.now();
    protected static final SecondBenefitId ANY_SECOND_BENEFIT_ID = SecondBenefitId.of("ANY_FIRST_BENEFIT_ID");
    protected static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");
    protected static final LocalDateTime ANY_EXPIRED_AT = LocalDateTime.now().plusDays(30);
    protected static final String ANY_ORDER_ID = "ANY_ORDER_ID";
    protected static final SecondBenefit ANY_SECOND_BENEFIT = SecondBenefit.of(
            ANY_SECOND_BENEFIT_ID,
            ANY_USER_ID,
            SecondBenefitType.ORDER,
            SecondBenefitStatus.AVAILABLE,
            SecondBenefitExpiredAt.of(ANY_EXPIRED_AT),
            SecondBenefitTargetMapping.of(ANY_ORDER_ID, 0L),
            NOW, NOW, 0L
    );
}
