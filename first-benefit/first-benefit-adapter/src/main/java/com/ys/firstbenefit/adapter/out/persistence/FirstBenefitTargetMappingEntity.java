package com.ys.firstbenefit.adapter.out.persistence;

import com.ys.firstbenefit.domain.FirstBenefit;
import com.ys.firstbenefit.domain.FirstBenefitTargetMapping;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "first_benefit_target_mappings")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Slf4j
@ToString
public class FirstBenefitTargetMappingEntity {

    @Id
    @Column(name = "first_benefit_id")
    private String firstBenefitId;
    @Column(name = "target_id", nullable = false)
    private String targetId;

    @Version
    private Long version;

    public static FirstBenefitTargetMappingEntity fromDomain(FirstBenefit firstBenefit) {
        FirstBenefitTargetMapping firstBenefitTargetMapping = firstBenefit.getFirstBenefitTargetMapping();
        return new FirstBenefitTargetMappingEntity(
                firstBenefit.getId().getId(),
                firstBenefitTargetMapping.getTargetId(),
                firstBenefitTargetMapping.getVersion()
        );
    }

    public FirstBenefitTargetMapping toDomain() {
        return FirstBenefitTargetMapping.of(
                this.targetId,
                this.version
        );
    }
}
