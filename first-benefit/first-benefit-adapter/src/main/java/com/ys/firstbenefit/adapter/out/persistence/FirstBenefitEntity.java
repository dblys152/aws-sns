package com.ys.firstbenefit.adapter.out.persistence;

import com.ys.firstbenefit.domain.*;
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
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Slf4j
@ToString
public class FirstBenefitEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FirstBenefitType type;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FirstBenefitStatus status;
    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
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
                firstBenefit.getType(),
                firstBenefit.getStatus(),
                firstBenefit.getExpiredAt().getValue(),
                FirstBenefitTargetMappingEntity.fromDomain(firstBenefit),
                firstBenefit.getCreatedAt(),
                firstBenefit.getModifiedAt(),
                firstBenefit.getVersion()
        );
    }

    public FirstBenefit toDomain() {
        return FirstBenefit.of(
                FirstBenefitId.of(this.id),
                UserId.of(this.userId),
                this.type,
                this.status,
                FirstBenefitExpiredAt.of(this.expiredAt),
                this.firstBenefitTargetMappingEntity.toDomain(),
                this.createdAt,
                this.modifiedAt,
                this.version
        );
    }
}
