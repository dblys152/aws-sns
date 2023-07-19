package com.ys.firstbenefit.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class FirstBenefitTargetMapping {

    @NotNull
    String targetId;

    Long version;

    public static FirstBenefitTargetMapping create(String targetId) {
        return new FirstBenefitTargetMapping(targetId, null);
    }
}
