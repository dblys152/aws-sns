package com.ys.secondbenefit.application.port.in;

import com.ys.secondbenefit.common.SelfValidating;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetSecondBenefitParams extends SelfValidating<GetSecondBenefitParams> {

    private String userId;

    @Builder
    public GetSecondBenefitParams(String userId) {
        this.userId = userId;
        validateSelf();
    }
}
