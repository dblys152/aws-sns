package com.ys.secondbenefit.adapter.in.model;

import com.ys.secondbenefit.domain.SecondBenefit;
import com.ys.secondbenefit.domain.SecondBenefitStatus;
import com.ys.secondbenefit.domain.SecondBenefitTargetMapping;
import com.ys.secondbenefit.domain.SecondBenefitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecondBenefitModel {

    String id;
    String userId;
    SecondBenefitType type;
    SecondBenefitStatus status;
    LocalDateTime expiredAt;
    FirstBenefitTargetMappingModel firstBenefitTargetMapping;

    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FirstBenefitTargetMappingModel {
        String targetId;
    }

    public static SecondBenefitModel fromDomain(SecondBenefit secondBenefit) {
        SecondBenefitTargetMapping secondBenefitTargetMapping = secondBenefit.getSecondBenefitTargetMapping();

        return new SecondBenefitModel(
                secondBenefit.getId().getId(),
                secondBenefit.getUserId().getId(),
                secondBenefit.getType(),
                secondBenefit.getStatus(),
                secondBenefit.getExpiredAt().getValue(),
                new FirstBenefitTargetMappingModel(secondBenefitTargetMapping.getTargetId()),
                secondBenefit.getCreatedAt(),
                secondBenefit.getModifiedAt()
        );
    }
}
