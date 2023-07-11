package com.ys.firstbenefit.adapter.out.persistence;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ys.firstbenefit.application.port.in.GetFirstBenefitParams;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirstBenefitEntityRepositorySupport {

    @PersistenceContext
    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final QFirstBenefitEntity firstBenefitEntity;
    private final QFirstBenefitTargetMappingEntity firstBenefitTargetMappingEntity;

    public FirstBenefitEntityRepositorySupport(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.firstBenefitEntity = QFirstBenefitEntity.firstBenefitEntity;
        this.firstBenefitTargetMappingEntity = QFirstBenefitTargetMappingEntity.firstBenefitTargetMappingEntity;
    }

    public List<FirstBenefitEntity> findAllByParams(GetFirstBenefitParams params) {
        BooleanBuilder builder = new BooleanBuilder();
        if (params.getUserId() != null) {
            builder.and(firstBenefitEntity.userId.eq(params.getUserId()));
        }

        return queryFactory.selectFrom(firstBenefitEntity)
                .innerJoin(firstBenefitTargetMappingEntity)
                .fetchJoin()
                .where(builder)
                .fetch();
    }
}
