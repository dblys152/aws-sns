package com.ys.firstbenefit.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class FirstBenefitTargetMapping {

    @NotNull
    FirstBenefitId firstBenefitId;
    @NotNull
    String targetId;
    @NotNull
    TargetType type;

    Long version;

    public static FirstBenefitTargetMapping create(FirstBenefitId firstBenefitId, String targetId, TargetType type) {
        return new FirstBenefitTargetMapping(firstBenefitId, targetId, type, null);
    }
}
