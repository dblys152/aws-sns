package com.ys.secondbenefit.application.service;

import com.ys.secondbenefit.application.port.in.GetSecondBenefitParams;
import com.ys.secondbenefit.application.port.out.LoadSecondBenefitPort;
import com.ys.secondbenefit.domain.SecondBenefits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class SecondBenefitQueryServiceTest {

    private static final String ANY_USER_ID = "ANY_USER_ID";

    @InjectMocks
    private SecondBenefitQueryService sut;

    @Mock
    private LoadSecondBenefitPort loadSecondBenefitPort;

    private SecondBenefits mockSecondBenefits;

    @BeforeEach
    void setUp() {
        mockSecondBenefits = mock(SecondBenefits.class);
    }

    @Test
    void second_benefit_목록_조회() {
        GetSecondBenefitParams params = GetSecondBenefitParams.builder().build();
        given(loadSecondBenefitPort.findAllByParams(params)).willReturn(mockSecondBenefits);

        SecondBenefits actual = sut.getAllByParams(params);

        assertThat(actual).isNotNull();
        then(loadSecondBenefitPort).should()
                .findAllByParams(params);
    }

    @Test
    void 유저의_second_benefit_목록_조회() {
        GetSecondBenefitParams params = GetSecondBenefitParams.builder()
                .userId(ANY_USER_ID)
                .build();
        given(loadSecondBenefitPort.findAllByParams(params)).willReturn(mockSecondBenefits);

        SecondBenefits actual = sut.getAllByParams(params);

        assertThat(actual).isNotNull();
        then(loadSecondBenefitPort).should()
                .findAllByParams(params);
    }
}