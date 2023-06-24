package com.ys.secondbenefit.domain;

import com.ys.refs.user.domain.UserId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SecondBenefitTest {

    private static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");
    private static final String ANY_TARGET_ID = "ANY_TARGET_ID";

    @Test
    void SECOND_BENEFIT을_생성한다() {
        SecondBenefit actual = SecondBenefit.create(ANY_USER_ID, SecondBenefitType.ORDER);
        actual.createTargetMapping(ANY_TARGET_ID);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getFirstBenefitTargetMapping()).isNotNull()
        );
    }
}