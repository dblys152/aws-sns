package com.ys.firstbenefit.domain;

import com.ys.refs.user.domain.UserId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FirstBenefitTest {

    private static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");
    private static final String ANY_TARGET_ID = "ANY_TARGET_ID";

    @Test
    void FIRST_BENEFIT을_생성한다() {
        CreateFirstBenefitCommand command = new CreateFirstBenefitCommand(
                ANY_USER_ID, ANY_TARGET_ID, FirstBenefitType.ORDER);

        FirstBenefit actual = FirstBenefit.create(command);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getFirstBenefitTargetMapping()).isNotNull()
        );
    }
}