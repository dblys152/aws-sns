package com.ys.firstbenefit.domain;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class FirstBenefits {

    List<FirstBenefit> items;

    public boolean isEmpty() {
        return this.items.isEmpty();
    }
}
