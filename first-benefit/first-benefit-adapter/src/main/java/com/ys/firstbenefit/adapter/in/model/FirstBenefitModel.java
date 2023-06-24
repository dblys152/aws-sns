package com.ys.firstbenefit.adapter.in.model;

import com.ys.firstbenefit.domain.FirstBenefit;
import com.ys.firstbenefit.domain.FirstBenefitStatus;
import com.ys.firstbenefit.domain.FirstBenefitTargetMapping;
import com.ys.firstbenefit.domain.FirstBenefitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstBenefitModel {

    String id;
    String userId;
    FirstBenefitType type;
    FirstBenefitStatus status;
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

    public static FirstBenefitModel fromDomain(FirstBenefit firstBenefit) {
        FirstBenefitTargetMapping firstBenefitTargetMapping = firstBenefit.getFirstBenefitTargetMapping();

        return new FirstBenefitModel(
                firstBenefit.getId().getId(),
                firstBenefit.getUserId().getId(),
                firstBenefit.getType(),
                firstBenefit.getStatus(),
                firstBenefit.getExpiredAt().getValue(),
                new FirstBenefitTargetMappingModel(firstBenefitTargetMapping.getTargetId()),
                firstBenefit.getCreatedAt(),
                firstBenefit.getModifiedAt()
        );
    }
}
