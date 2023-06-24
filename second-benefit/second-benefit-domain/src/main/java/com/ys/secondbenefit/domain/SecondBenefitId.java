package com.ys.secondbenefit.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class SecondBenefitId {

    @NotNull
    String id;
}
