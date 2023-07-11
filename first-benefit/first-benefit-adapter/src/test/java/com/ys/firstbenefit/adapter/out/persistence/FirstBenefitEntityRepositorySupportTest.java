package com.ys.firstbenefit.adapter.out.persistence;

import com.ys.firstbenefit.adapter.config.DataJpaConfig;
import com.ys.firstbenefit.adapter.out.persistence.fixture.SupportFirstBenefitEntityFixture;
import com.ys.firstbenefit.application.port.in.GetFirstBenefitParams;
import com.ys.firstbenefit.domain.FirstBenefitStatus;
import com.ys.firstbenefit.domain.FirstBenefitType;
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
class FirstBenefitEntityRepositorySupportTest extends SupportFirstBenefitEntityFixture {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private FirstBenefitEntityRepository repository;

    private FirstBenefitEntityRepositorySupport repositorySupport;

    private FirstBenefitEntity entity;

    @BeforeEach
    void setUp() {
        this.repositorySupport = new FirstBenefitEntityRepositorySupport(entityManager);
        entity = new FirstBenefitEntity(
                ANY_FIRST_BENEFIT_ID,
                ANY_USER_ID,
                FirstBenefitType.ORDER,
                FirstBenefitStatus.AVAILABLE,
                ANY_EXPIRED_AT,
                new FirstBenefitTargetMappingEntity(ANY_FIRST_BENEFIT_ID, ANY_ORDER_ID, null),
                null, null, null);
    }

    @Test
    void 전체_목록_조회() {
        FirstBenefitEntity saved = repository.save(entity);

        List<FirstBenefitEntity> actual = repositorySupport.findAllByParams(GetFirstBenefitParams.builder().build());

        assertAll(
                () -> assertThat(actual).isNotEmpty()
        );
    }

    @Test
    void 유저의_전체_목록_조회() {
        FirstBenefitEntity saved = repository.save(entity);
        GetFirstBenefitParams params = GetFirstBenefitParams.builder()
                .userId(ANY_USER_ID)
                .build();

        List<FirstBenefitEntity> actual = repositorySupport.findAllByParams(params);

        assertAll(
                () -> assertThat(actual).isNotEmpty(),
                () -> assertThat(actual.get(0).getUserId()).isEqualTo(saved.getUserId())
        );
    }
}