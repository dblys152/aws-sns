package com.ys.secondbenefit.application;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.application.port.in.CreateSecondBenefitCommand;
import com.ys.secondbenefit.application.port.out.RecordSecondBenefitPort;
import com.ys.secondbenefit.application.service.SecondBenefitCommandService;
import com.ys.secondbenefit.domain.*;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SecondBenefitCommandServiceTest {

    private static final LocalDateTime NOW = LocalDateTime.now();
    private static final SecondBenefitId ANY_ID = SecondBenefitId.of("ANY_ID");
    private static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");
    private static final SecondBenefitExpiredAt ANY_EXPIRED_AT = SecondBenefitExpiredAt.of(NOW.plusDays(30));
    private static final String ANY_TARGET_ID = "ANY_TARGET_ID";

    @InjectMocks
    private SecondBenefitCommandService sut;

    @Mock
    private RecordSecondBenefitPort recordSecondBenefitPort;

    private SecondBenefit secondBenefit;

    @BeforeEach
    void setUp() {
        secondBenefit = SecondBenefit.of(
                ANY_ID,
                ANY_USER_ID,
                SecondBenefitType.ORDER,
                SecondBenefitStatus.AVAILABLE,
                ANY_EXPIRED_AT,
                SecondBenefitTargetMapping.of(ANY_ID, ANY_TARGET_ID, 0L),
                NOW, NOW, 0L
        );
    }

    @Test
    void FIRST_BENEFIT_생성() {
        CreateSecondBenefitCommand command = new CreateSecondBenefitCommand(
                ANY_USER_ID, ANY_TARGET_ID, SecondBenefitType.ORDER);
        given(recordSecondBenefitPort.save(any(SecondBenefit.class))).willReturn(secondBenefit);

        SecondBenefit actual = sut.create(command);

        assertThat(actual).isNotNull();
        verify(recordSecondBenefitPort).save(any(SecondBenefit.class));
    }
}