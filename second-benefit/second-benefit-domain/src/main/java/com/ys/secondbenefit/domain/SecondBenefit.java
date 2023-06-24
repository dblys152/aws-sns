package com.ys.secondbenefit.domain;

import com.fasterxml.uuid.Generators;
import com.ys.refs.user.domain.UserId;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.ys.secondbenefit.domain.SecondBenefitStatus.AVAILABLE;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SecondBenefit {

    @NotNull
    private SecondBenefitId id;
    @NotNull
    private UserId userId;
    @NotNull
    private SecondBenefitType type;
    @NotNull
    private SecondBenefitStatus status;
    @NotNull
    private SecondBenefitExpiredAt expiredAt;

    private SecondBenefitTargetMapping secondBenefitTargetMapping;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long version;

    private SecondBenefit(
            SecondBenefitId id,
            UserId userId,
            SecondBenefitType type,
            SecondBenefitStatus status,
            SecondBenefitExpiredAt expiredAt
    ) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.status = status;
        this.expiredAt = expiredAt;
    }

    public static SecondBenefit create(UserId userId, SecondBenefitType type) {
        SecondBenefitId id = SecondBenefitId.of(Generators.timeBasedEpochGenerator().generate().toString());
        return new SecondBenefit(id, userId, type, AVAILABLE, SecondBenefitExpiredAt.create(type));
    }

    public void createTargetMapping(String targetId) {
        this.secondBenefitTargetMapping = SecondBenefitTargetMapping.create(this.id, targetId);
    }

    public static SecondBenefit of(
            SecondBenefitId id,
            UserId userId,
            SecondBenefitType type,
            SecondBenefitStatus status,
            SecondBenefitExpiredAt expiredAt,
            SecondBenefitTargetMapping secondBenefitTargetMapping,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Long version
    ) {
        return new SecondBenefit(id, userId, type, status, expiredAt, secondBenefitTargetMapping, createdAt, modifiedAt, version);
    }
}
