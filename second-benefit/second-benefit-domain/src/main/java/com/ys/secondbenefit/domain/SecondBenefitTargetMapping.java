package com.ys.secondbenefit.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class SecondBenefitTargetMapping {

    @NotNull
    String targetId;

    Long version;

    public static SecondBenefitTargetMapping create(String targetId) {
        return new SecondBenefitTargetMapping(targetId, null);
    }
}
