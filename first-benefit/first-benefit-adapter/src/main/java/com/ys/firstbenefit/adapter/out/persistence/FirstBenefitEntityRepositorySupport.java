package com.ys.firstbenefit.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirstBenefitEntityRepositorySupport {

    @PersistenceContext
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public FirstBenefitEntityRepositorySupport(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<FirstBenefitEntity> findAllByUserId(String userId) {
        QFirstBenefitEntity firstBenefitEntity = QFirstBenefitEntity.firstBenefitEntity;
        QFirstBenefitTargetMappingEntity firstBenefitTargetMappingEntity = QFirstBenefitTargetMappingEntity.firstBenefitTargetMappingEntity;

        return queryFactory.selectFrom(firstBenefitEntity)
                .innerJoin(firstBenefitTargetMappingEntity)
                .fetchJoin()
                .where(firstBenefitEntity.userId.eq(userId))
                .fetch();
    }
}
