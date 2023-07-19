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
    private FirstBenefitType type;
    @NotNull
    private FirstBenefitStatus status;
    @NotNull
    private FirstBenefitExpiredAt expiredAt;

    private FirstBenefitTargetMapping firstBenefitTargetMapping;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long version;

    private FirstBenefit(
            FirstBenefitId id,
            UserId userId,
            FirstBenefitType type,
            FirstBenefitStatus status,
            FirstBenefitExpiredAt expiredAt,
            FirstBenefitTargetMapping firstBenefitTargetMapping
    ) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.status = status;
        this.expiredAt = expiredAt;
        this.firstBenefitTargetMapping = firstBenefitTargetMapping;
    }

    public static FirstBenefit create(CreateFirstBenefitCommand command) {
        FirstBenefitId id = FirstBenefitId.of(Generators.timeBasedEpochGenerator().generate().toString());
        return new FirstBenefit(
                id,
                command.getUserId(),
                command.getType(),
                AVAILABLE,
                FirstBenefitExpiredAt.create(command.getType()),
                FirstBenefitTargetMapping.create(command.getTargetId())
        );
    }

    public static FirstBenefit of(
            FirstBenefitId id,
            UserId userId,
            FirstBenefitType type,
            FirstBenefitStatus status,
            FirstBenefitExpiredAt expiredAt,
            FirstBenefitTargetMapping firstBenefitTargetMapping,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Long version
    ) {
        return new FirstBenefit(id, userId, type, status, expiredAt, firstBenefitTargetMapping, createdAt, modifiedAt, version);
    }
}
