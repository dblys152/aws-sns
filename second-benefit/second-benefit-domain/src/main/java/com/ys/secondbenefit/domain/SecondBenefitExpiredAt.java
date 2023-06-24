package com.ys.secondbenefit.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SecondBenefitExpiredAt {

    private LocalDateTime value;

    public static SecondBenefitExpiredAt create(SecondBenefitType type) {
        return new SecondBenefitExpiredAt(getExpiredAtByPolicy(type));
    }

    private static LocalDateTime getExpiredAtByPolicy(SecondBenefitType type) {
        // type에 따른 정책에서 만료 기간 설정 로직이 있다고 가정
        int ORDER_POLICY_DAY = 30;
        return LocalDateTime.now().plusDays(ORDER_POLICY_DAY);
    }

    public static SecondBenefitExpiredAt of(LocalDateTime expiredAt) {
        return new SecondBenefitExpiredAt(expiredAt);
    }
}
