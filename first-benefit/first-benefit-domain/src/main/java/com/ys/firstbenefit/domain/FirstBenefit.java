package com.ys.firstbenefit.domain;

import com.fasterxml.uuid.Generators;
import com.ys.refs.user.domain.UserId;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.ys.firstbenefit.domain.FirstBenefitStatus.AVAILABLE;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FirstBenefit {

    @NotNull
    private FirstBenefitId id;
    @NotNull
    private UserId userId;
    @NotNull
    private FirstBenefitStatus status;

    private FirstBenefitTargetMapping firstBenefitTargetMapping;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long version;

    private FirstBenefit(FirstBenefitId id, UserId userId, FirstBenefitStatus status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
    }

    public static FirstBenefit create(UserId userId) {
        FirstBenefitId id = FirstBenefitId.of(Generators.timeBasedEpochGenerator().generate().toString());
        return new FirstBenefit(id, userId, AVAILABLE);
    }

    public void createTargetMapping(String targetId, TargetType type) {
        this.firstBenefitTargetMapping = FirstBenefitTargetMapping.create(this.id, targetId, type);
    }

    public static FirstBenefit of(
            FirstBenefitId id,
            UserId userId,
            FirstBenefitStatus status,
            FirstBenefitTargetMapping firstBenefitTargetMapping,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Long version
    ) {
        return new FirstBenefit(id, userId, status, firstBenefitTargetMapping, createdAt, modifiedAt, version);
    }
}
