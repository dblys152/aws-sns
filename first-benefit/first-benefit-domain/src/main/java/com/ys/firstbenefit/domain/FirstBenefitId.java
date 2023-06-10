package com.ys.firstbenefit.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class FirstBenefitId {

    @NotNull
    String id;
}
