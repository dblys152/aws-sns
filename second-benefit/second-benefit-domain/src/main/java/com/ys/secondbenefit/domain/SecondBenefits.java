package com.ys.secondbenefit.domain;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class SecondBenefits {

    List<SecondBenefit> items;
}
