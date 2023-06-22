package com.ys.firstbenefit.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FirstBenefitExpiredAtTest {

    @Test
    void 타입_정책에_의한_만료_기간_설정() {
        FirstBenefitExpiredAt actual = FirstBenefitExpiredAt.create(FirstBenefitType.ORDER);

        assertThat(actual).isNotNull();
    }
}