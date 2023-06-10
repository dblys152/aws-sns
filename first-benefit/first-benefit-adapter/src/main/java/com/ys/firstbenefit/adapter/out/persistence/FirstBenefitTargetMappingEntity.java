package com.ys.firstbenefit.adapter.out.persistence;

import com.ys.firstbenefit.domain.FirstBenefitId;
import com.ys.firstbenefit.domain.FirstBenefitTargetMapping;
import com.ys.firstbenefit.domain.TargetType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "first_benefit_target_mappings")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Slf4j
@ToString
public class FirstBenefitTargetMappingEntity {

    @Id
    @Column(name = "first_benefit_id")
    private String firstBenefitId;
    @Column(name = "target_id")
    private String targetId;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TargetType type;

    @Version
    private Long version;

    public static FirstBenefitTargetMappingEntity fromDomain(FirstBenefitTargetMapping firstBenefitTargetMapping) {
        return new FirstBenefitTargetMappingEntity(
                firstBenefitTargetMapping.getFirstBenefitId().getId(),
                firstBenefitTargetMapping.getTargetId(),
                firstBenefitTargetMapping.getType(),
                firstBenefitTargetMapping.getVersion()
        );
    }

    public FirstBenefitTargetMapping toDomain() {
        return FirstBenefitTargetMapping.of(
                FirstBenefitId.of(this.firstBenefitId),
                this.targetId,
                this.type,
                this.version
        );
    }
}
