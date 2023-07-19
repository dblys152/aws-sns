package com.ys.firstbenefit.application.service;

import com.ys.firstbenefit.application.port.out.RecordFirstBenefitPort;
import com.ys.firstbenefit.domain.*;
import com.ys.refs.user.domain.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class FirstBenefitCommandServiceTest {

    private static final LocalDateTime NOW = LocalDateTime.now();
    private static final FirstBenefitId ANY_ID = FirstBenefitId.of("ANY_ID");
    private static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");
    private static final FirstBenefitExpiredAt ANY_EXPIRED_AT = FirstBenefitExpiredAt.of(NOW.plusDays(30));
    private static final String ANY_TARGET_ID = "ANY_TARGET_ID";

    @InjectMocks
    private FirstBenefitCommandService sut;

    @Mock
    private RecordFirstBenefitPort recordFirstBenefitPort;

    private FirstBenefit firstBenefit;

    @BeforeEach
    void setUp() {
        firstBenefit = FirstBenefit.of(
                ANY_ID,
                ANY_USER_ID,
                FirstBenefitType.ORDER,
                FirstBenefitStatus.AVAILABLE,
                ANY_EXPIRED_AT,
                FirstBenefitTargetMapping.of(ANY_TARGET_ID, 0L),
                NOW, NOW, 0L
        );
    }

    @Test
    void FIRST_BENEFIT_생성() {
        CreateFirstBenefitCommand command = new CreateFirstBenefitCommand(
                ANY_USER_ID, ANY_TARGET_ID, FirstBenefitType.ORDER);
        given(recordFirstBenefitPort.save(any(FirstBenefit.class))).willReturn(firstBenefit);

        FirstBenefit actual = sut.create(command);

        assertThat(actual).isNotNull();
        then(recordFirstBenefitPort).should()
                .save(any(FirstBenefit.class));
    }
}