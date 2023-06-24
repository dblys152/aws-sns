package com.ys.secondbenefit.application;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.application.port.out.LoadSecondBenefitPort;
import com.ys.secondbenefit.application.service.SecondBenefitQueryService;
import com.ys.secondbenefit.domain.SecondBenefits;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SecondBenefitQueryServiceTest {

    private static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");

    @InjectMocks
    private SecondBenefitQueryService sut;

    @Mock
    private LoadSecondBenefitPort loadSecondBenefitPort;

    @Test
    void second_benefit_목록_조회() {
        given(loadSecondBenefitPort.findAllByUserId(ANY_USER_ID)).willReturn(mock(SecondBenefits.class));

        SecondBenefits actual = sut.getAllByUserId(ANY_USER_ID);

        assertThat(actual).isNotNull();
        verify(loadSecondBenefitPort).findAllByUserId(ANY_USER_ID);
    }
}