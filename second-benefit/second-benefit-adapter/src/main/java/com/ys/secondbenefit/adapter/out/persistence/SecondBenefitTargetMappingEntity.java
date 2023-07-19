package com.ys.secondbenefit.adapter.out.persistence;

import com.ys.secondbenefit.domain.SecondBenefit;
import com.ys.secondbenefit.domain.SecondBenefitTargetMapping;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "second_benefit_target_mappings")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Slf4j
@ToString
public class SecondBenefitTargetMappingEntity {

    @Id
    @Column(name = "second_benefit_id")
    private String secondBenefitId;
    @Column(name = "target_id", nullable = false)
    private String targetId;

    @Version
    private Long version;

    public static SecondBenefitTargetMappingEntity fromDomain(SecondBenefit secondBenefit) {
        SecondBenefitTargetMapping secondBenefitTargetMapping = secondBenefit.getSecondBenefitTargetMapping();
        return new SecondBenefitTargetMappingEntity(
                secondBenefit.getId().getId(),
                secondBenefitTargetMapping.getTargetId(),
                secondBenefitTargetMapping.getVersion()
        );
    }

    public SecondBenefitTargetMapping toDomain() {
        return SecondBenefitTargetMapping.of(
                this.targetId,
                this.version
        );
    }
}
