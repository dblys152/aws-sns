package com.ys.secondbenefit.adapter.in;

import com.ys.secondbenefit.adapter.in.fixture.SupportSecondBenefitFixture;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitParams;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitQuery;
import com.ys.secondbenefit.domain.SecondBenefits;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SecondBenefitQueryController.class)
class SecondBenefitQueryControllerTest extends SupportSecondBenefitFixture {

    private static final String SECOND_BENEFITS_ENDPOINT = "/v1/second-benefits";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetSecondBenefitQuery getSecondBenefitQuery;

    @Test
    void second_benefit_목록_조회() throws Exception {
        SecondBenefits firstBenefits = SecondBenefits.of(Arrays.asList(ANY_SECOND_BENEFIT));
        given(getSecondBenefitQuery.getAllByParams(any(GetSecondBenefitParams.class))).willReturn(firstBenefits);

        mockMvc.perform(get(SECOND_BENEFITS_ENDPOINT))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(ANY_SECOND_BENEFIT_ID.getId()));

        then(getSecondBenefitQuery).should()
                .getAllByParams(any(GetSecondBenefitParams.class));
    }

    @Test
    void 유저의_second_benefit_목록_조회() throws Exception {
        SecondBenefits firstBenefits = SecondBenefits.of(Arrays.asList(ANY_SECOND_BENEFIT));
        given(getSecondBenefitQuery.getAllByParams(any(GetSecondBenefitParams.class))).willReturn(firstBenefits);
        String userId = ANY_USER_ID.getId();

        mockMvc.perform(get(SECOND_BENEFITS_ENDPOINT)
                        .param("userId", userId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(ANY_SECOND_BENEFIT_ID.getId()))
                .andExpect(jsonPath("$.data[0].userId").value(userId));

        then(getSecondBenefitQuery).should()
                .getAllByParams(argThat(params -> params.getUserId().equals(userId)));
    }

    @Test
    public void 데이터가_존재하지_않으면_404로_응답한다() throws Exception {
        SecondBenefits firstBenefits = SecondBenefits.of(new ArrayList<>());
        given(getSecondBenefitQuery.getAllByParams(any(GetSecondBenefitParams.class))).willReturn(firstBenefits);

        mockMvc.perform(get(SECOND_BENEFITS_ENDPOINT))
                .andExpect(status().isNotFound());

        then(getSecondBenefitQuery).should()
                .getAllByParams(any(GetSecondBenefitParams.class));
    }
}