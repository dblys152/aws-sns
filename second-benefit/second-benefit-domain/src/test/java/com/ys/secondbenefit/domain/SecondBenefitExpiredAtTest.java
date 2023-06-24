package com.ys.secondbenefit.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SecondBenefitExpiredAtTest {

    @Test
    void 타입_정책에_의한_만료_기간_설정() {
        SecondBenefitExpiredAt actual = SecondBenefitExpiredAt.create(SecondBenefitType.ORDER);

        assertThat(actual).isNotNull();
    }
}