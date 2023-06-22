package com.ys.firstbenefit.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class FirstBenefitTargetMapping {

    @NotNull
    FirstBenefitId firstBenefitId;
    @NotNull
    String targetId;

    Long version;

    public static FirstBenefitTargetMapping create(FirstBenefitId firstBenefitId, String targetId) {
        return new FirstBenefitTargetMapping(firstBenefitId, targetId, null);
    }
}
