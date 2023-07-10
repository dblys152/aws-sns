package com.ys.firstbenefit.application.service;

import com.ys.firstbenefit.application.port.in.GetFirstBenefitParams;
import com.ys.firstbenefit.application.port.out.LoadFirstBenefitPort;
import com.ys.firstbenefit.domain.FirstBenefits;
import com.ys.refs.user.domain.UserId;
import org.junit.jupiter.api.BeforeEach;
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
class FirstBenefitQueryServiceTest {

    private static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");

    @InjectMocks
    private FirstBenefitQueryService sut;

    @Mock
    private LoadFirstBenefitPort loadFirstBenefitPort;

    private FirstBenefits mockFirstBenefits;

    @BeforeEach
    void setUp() {
        mockFirstBenefits = mock(FirstBenefits.class);
    }

    @Test
    void first_benefit_목록_조회() {
        GetFirstBenefitParams params = GetFirstBenefitParams.builder().build();
        given(loadFirstBenefitPort.findAllByParams(params)).willReturn(mockFirstBenefits);

        FirstBenefits actual = sut.getAllByParams(params);

        assertThat(actual).isNotNull();
        verify(loadFirstBenefitPort).findAllByParams(params);
    }

    @Test
    void 유저의_first_benefit_목록_조회() {
        GetFirstBenefitParams params = GetFirstBenefitParams.builder()
                .userId(ANY_USER_ID)
                .build();
        given(loadFirstBenefitPort.findAllByParams(params)).willReturn(mockFirstBenefits);

        FirstBenefits actual = sut.getAllByParams(params);

        assertThat(actual).isNotNull();
        verify(loadFirstBenefitPort).findAllByParams(params);
    }
}