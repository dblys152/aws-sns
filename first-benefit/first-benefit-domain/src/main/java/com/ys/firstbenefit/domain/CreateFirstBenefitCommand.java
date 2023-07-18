package com.ys.firstbenefit.domain;

import com.ys.refs.user.domain.UserId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateFirstBenefitCommand extends SelfValidating<CreateFirstBenefitCommand> {

    @Valid @NotNull
    private UserId userId;
    @NotNull
    private String targetId;
    @NotNull
    private FirstBenefitType type;

    public CreateFirstBenefitCommand(UserId userId, String targetId, FirstBenefitType type) {
        this.userId = userId;
        this.targetId = targetId;
        this.type = type;
        validateSelf();
    }
}
