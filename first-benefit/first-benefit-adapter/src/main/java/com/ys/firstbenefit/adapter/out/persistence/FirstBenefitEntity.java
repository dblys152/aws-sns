package com.ys.firstbenefit.adapter.out.persistence;

import com.ys.firstbenefit.domain.FirstBenefit;
import com.ys.firstbenefit.domain.FirstBenefitId;
import com.ys.firstbenefit.domain.FirstBenefitStatus;
import com.ys.refs.user.domain.UserId;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "first_benefits")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Slf4j
@ToString
public class FirstBenefitEntity {

    @Id
    private String id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FirstBenefitStatus status;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "first_benefit_id")
    private FirstBenefitTargetMappingEntity firstBenefitTargetMappingEntity;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @Version
    private Long version;

    public static FirstBenefitEntity fromDomain(FirstBenefit firstBenefit) {
        return new FirstBenefitEntity(
                firstBenefit.getId().getId(),
                firstBenefit.getUserId().getId(),
                firstBenefit.getStatus(),
                FirstBenefitTargetMappingEntity.fromDomain(firstBenefit.getFirstBenefitTargetMapping()),
                firstBenefit.getCreatedAt(),
                firstBenefit.getModifiedAt(),
                firstBenefit.getVersion()
        );
    }

    public FirstBenefit toDomain() {
        return FirstBenefit.of(
                FirstBenefitId.of(this.id),
                UserId.of(this.userId),
                this.status,
                this.firstBenefitTargetMappingEntity.toDomain(),
                this.createdAt,
                this.modifiedAt,
                this.version
        );
    }
}
