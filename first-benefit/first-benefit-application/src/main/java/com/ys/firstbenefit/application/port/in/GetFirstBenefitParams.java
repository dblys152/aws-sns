package com.ys.firstbenefit.application.port.in;

import com.ys.firstbenefit.common.SelfValidating;
import com.ys.refs.user.domain.UserId;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetFirstBenefitParams extends SelfValidating<GetFirstBenefitParams> {

    private UserId userId;

    @Builder
    public GetFirstBenefitParams(UserId userId) {
        this.userId = userId;
        validateSelf();
    }
}
