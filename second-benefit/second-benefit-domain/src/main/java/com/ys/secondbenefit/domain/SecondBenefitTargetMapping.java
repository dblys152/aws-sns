package com.ys.secondbenefit.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class SecondBenefitTargetMapping {

    @NotNull
    SecondBenefitId secondBenefitId;
    @NotNull
    String targetId;

    Long version;

    public static SecondBenefitTargetMapping create(SecondBenefitId secondBenefitId, String targetId) {
        return new SecondBenefitTargetMapping(secondBenefitId, targetId, null);
    }
}
