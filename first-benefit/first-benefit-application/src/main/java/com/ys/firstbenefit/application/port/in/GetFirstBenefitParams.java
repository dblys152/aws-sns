package com.ys.firstbenefit.application.port.in;

import com.ys.firstbenefit.domain.SelfValidating;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetFirstBenefitParams extends SelfValidating<GetFirstBenefitParams> {

    private String userId;

    @Builder
    public GetFirstBenefitParams(String userId) {
        this.userId = userId;
        validateSelf();
    }
}
