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
        CreateSecondBenefitCommand command = new CreateSecondBenefitCommand(
                ANY_USER_ID, ANY_TARGET_ID, SecondBenefitType.ORDER);

        SecondBenefit actual = SecondBenefit.create(command);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getSecondBenefitTargetMapping()).isNotNull()
        );
    }
}