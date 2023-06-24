package com.ys.secondbenefit.application.port.in;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.common.SelfValidating;
import com.ys.secondbenefit.domain.SecondBenefitType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateSecondBenefitCommand extends SelfValidating<CreateSecondBenefitCommand> {

    @Valid @NotNull
    private UserId userId;
    @NotNull
    private String targetId;
    @NotNull
    private SecondBenefitType type;

    public CreateSecondBenefitCommand(UserId userId, String targetId, SecondBenefitType type) {
        this.userId = userId;
        this.targetId = targetId;
        this.type = type;
        validateSelf();
    }
}
