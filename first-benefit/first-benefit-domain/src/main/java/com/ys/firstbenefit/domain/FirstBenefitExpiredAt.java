package com.ys.firstbenefit.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirstBenefitExpiredAt {

    private LocalDateTime value;

    public static FirstBenefitExpiredAt create(FirstBenefitType type) {
        return new FirstBenefitExpiredAt(getExpiredAtByPolicy(type));
    }

    private static LocalDateTime getExpiredAtByPolicy(FirstBenefitType type) {
        // type에 따른 정책에서 만료 기간 설정 로직이 있다고 가정
        int ORDER_POLICY_DAY = 30;
        return LocalDateTime.now().plusDays(ORDER_POLICY_DAY);
    }

    public static FirstBenefitExpiredAt of(LocalDateTime expiredAt) {
        return new FirstBenefitExpiredAt(expiredAt);
    }
}
