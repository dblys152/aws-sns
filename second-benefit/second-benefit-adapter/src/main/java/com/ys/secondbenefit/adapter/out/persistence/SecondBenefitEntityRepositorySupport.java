package com.ys.secondbenefit.adapter.out.persistence;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitParams;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecondBenefitEntityRepositorySupport {

    @PersistenceContext
    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final QSecondBenefitEntity secondBenefitEntity;
    private final QSecondBenefitTargetMappingEntity secondBenefitTargetMappingEntity;


    public SecondBenefitEntityRepositorySupport(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.secondBenefitEntity = QSecondBenefitEntity.secondBenefitEntity;
        this.secondBenefitTargetMappingEntity = QSecondBenefitTargetMappingEntity.secondBenefitTargetMappingEntity;
    }

    public List<SecondBenefitEntity> findAllByParams(GetSecondBenefitParams params) {
        BooleanBuilder builder = new BooleanBuilder();
        if (params.getUserId() != null) {
            builder.and(secondBenefitEntity.userId.eq(params.getUserId()));
        }

        return queryFactory.selectFrom(secondBenefitEntity)
                .leftJoin(secondBenefitTargetMappingEntity)
                .on(secondBenefitEntity.id.eq(secondBenefitTargetMappingEntity.secondBenefitId))
                .fetchJoin()
                .where(builder)
                .fetch();
    }
}
