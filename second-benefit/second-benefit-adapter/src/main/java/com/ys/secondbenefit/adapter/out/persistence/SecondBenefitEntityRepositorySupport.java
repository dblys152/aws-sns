package com.ys.secondbenefit.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecondBenefitEntityRepositorySupport {

    @PersistenceContext
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public SecondBenefitEntityRepositorySupport(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<SecondBenefitEntity> findAllByUserId(String userId) {
        QSecondBenefitEntity secondBenefitEntity = QSecondBenefitEntity.secondBenefitEntity;
        QSecondBenefitTargetMappingEntity secondBenefitTargetMappingEntity = QSecondBenefitTargetMappingEntity.secondBenefitTargetMappingEntity;

        return queryFactory.selectFrom(secondBenefitEntity)
                .innerJoin(secondBenefitTargetMappingEntity)
                .fetchJoin()
                .where(secondBenefitEntity.userId.eq(userId))
                .fetch();
    }
}
