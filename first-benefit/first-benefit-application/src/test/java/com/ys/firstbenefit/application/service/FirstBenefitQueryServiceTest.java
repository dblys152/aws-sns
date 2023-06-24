package com.ys.firstbenefit.application.service;

import com.ys.firstbenefit.application.port.out.LoadFirstBenefitPort;
import com.ys.firstbenefit.domain.FirstBenefits;
import com.ys.refs.user.domain.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void first_benefit_목록_조회() {
        given(loadFirstBenefitPort.findAllByUserId(ANY_USER_ID)).willReturn(mock(FirstBenefits.class));

        FirstBenefits actual = sut.getAllByUserId(ANY_USER_ID);

        assertThat(actual).isNotNull();
        verify(loadFirstBenefitPort).findAllByUserId(ANY_USER_ID);
    }
}