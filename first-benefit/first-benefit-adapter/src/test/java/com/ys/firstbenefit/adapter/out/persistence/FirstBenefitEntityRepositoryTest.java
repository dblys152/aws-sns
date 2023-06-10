package com.ys.firstbenefit.adapter.out.persistence;

import com.ys.firstbenefit.adapter.config.DataJpaConfig;
import com.ys.firstbenefit.domain.FirstBenefitStatus;
import com.ys.firstbenefit.domain.TargetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DataJpaConfig.class)
class FirstBenefitEntityRepositoryTest {

    private static final String ANY_USER_ID = "ANY_USER_ID";
    private static final String ANY_ORDER_ID = "ANY_ORDER_ID";

    @Autowired
    private FirstBenefitEntityRepository repository;

    private FirstBenefitEntity entity;

    @BeforeEach
    void setUp() {
        String FIRST_BENEFIT_ID = "FIRST_BENEFIT_ID";
        entity = new FirstBenefitEntity(
                FIRST_BENEFIT_ID,
                ANY_USER_ID,
                FirstBenefitStatus.AVAILABLE,
                new FirstBenefitTargetMappingEntity(FIRST_BENEFIT_ID, ANY_ORDER_ID, TargetType.ORDER, null),
                null, null, null);
    }

    @Test
    void save() {
        FirstBenefitEntity actual = repository.save(entity);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isEqualTo(entity.getId()),
                () -> assertThat(actual.getFirstBenefitTargetMappingEntity()).isNotNull()
        );
    }
}