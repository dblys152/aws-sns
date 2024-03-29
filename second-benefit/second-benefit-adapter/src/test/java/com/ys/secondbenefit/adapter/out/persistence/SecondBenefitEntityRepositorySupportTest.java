package com.ys.secondbenefit.adapter.out.persistence;

import com.ys.secondbenefit.adapter.config.DataJpaConfig;
import com.ys.secondbenefit.adapter.out.persistence.fixture.SupportSecondBenefitEntityFixture;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitParams;
import com.ys.secondbenefit.domain.SecondBenefitStatus;
import com.ys.secondbenefit.domain.SecondBenefitType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DataJpaConfig.class)
class SecondBenefitEntityRepositorySupportTest extends SupportSecondBenefitEntityFixture {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private SecondBenefitEntityRepository repository;

    private SecondBenefitEntityRepositorySupport repositorySupport;

    private SecondBenefitEntity entity;

    @BeforeEach
    void setUp() {
        this.repositorySupport = new SecondBenefitEntityRepositorySupport(entityManager);
        entity = new SecondBenefitEntity(
                ANY_SECOND_BENEFIT_ID,
                ANY_USER_ID,
                SecondBenefitType.ORDER,
                SecondBenefitStatus.AVAILABLE,
                ANY_EXPIRED_AT,
                new SecondBenefitTargetMappingEntity(ANY_SECOND_BENEFIT_ID, ANY_ORDER_ID, null),
                null, null, null);
    }

    @Test
    void 전체_목록_조회() {
        SecondBenefitEntity saved = repository.save(entity);

        List<SecondBenefitEntity> actual = repositorySupport.findAllByParams(GetSecondBenefitParams.builder().build());

        assertAll(
                () -> assertThat(actual).isNotEmpty(),
                () -> assertThat( actual.get(0).getSecondBenefitTargetMappingEntity()).isNotNull()
        );
    }

    @Test
    void 유저의_전체_목록_조회() {
        SecondBenefitEntity saved = repository.save(entity);
        GetSecondBenefitParams params = GetSecondBenefitParams.builder()
                .userId(ANY_USER_ID)
                .build();

        List<SecondBenefitEntity> actual = repositorySupport.findAllByParams(params);

        assertAll(
                () -> assertThat(actual).isNotEmpty(),
                () -> assertThat(actual.get(0).getUserId()).isEqualTo(saved.getUserId())
        );
    }
}