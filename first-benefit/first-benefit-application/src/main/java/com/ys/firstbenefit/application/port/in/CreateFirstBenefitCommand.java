package com.ys.firstbenefit.application.port.in;

import com.ys.firstbenefit.common.SelfValidating;
import com.ys.firstbenefit.domain.TargetType;
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
    private TargetType type;

    public CreateFirstBenefitCommand(UserId userId, String targetId, TargetType type) {
        this.userId = userId;
        this.targetId = targetId;
        this.type = type;
        validateSelf();
    }
}
