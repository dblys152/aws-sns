package com.ys.secondbenefit.adapter.out.persistence;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.domain.*;
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
public class SecondBenefitEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SecondBenefitType type;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SecondBenefitStatus status;
    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    private SecondBenefitTargetMappingEntity secondBenefitTargetMappingEntity;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @Version
    private Long version;

    public static SecondBenefitEntity fromDomain(SecondBenefit secondBenefit) {
        return new SecondBenefitEntity(
                secondBenefit.getId().getId(),
                secondBenefit.getUserId().getId(),
                secondBenefit.getType(),
                secondBenefit.getStatus(),
                secondBenefit.getExpiredAt().getValue(),
                SecondBenefitTargetMappingEntity.fromDomain(secondBenefit.getSecondBenefitTargetMapping()),
                secondBenefit.getCreatedAt(),
                secondBenefit.getModifiedAt(),
                secondBenefit.getVersion()
        );
    }

    public SecondBenefit toDomain() {
        return SecondBenefit.of(
                SecondBenefitId.of(this.id),
                UserId.of(this.userId),
                this.type,
                this.status,
                SecondBenefitExpiredAt.of(this.expiredAt),
                this.secondBenefitTargetMappingEntity.toDomain(),
                this.createdAt,
                this.modifiedAt,
                this.version
        );
    }
}
