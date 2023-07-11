package com.ys.firstbenefit.adapter.in;

import com.ys.firstbenefit.adapter.in.fixture.SupportFirstBenefitFixture;
import com.ys.firstbenefit.application.port.in.GetFirstBenefitParams;
import com.ys.firstbenefit.application.port.in.GetFirstBenefitQuery;
import com.ys.firstbenefit.domain.FirstBenefits;
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

@WebMvcTest(controllers = FirstBenefitQueryController.class)
class FirstBenefitQueryControllerTest extends SupportFirstBenefitFixture {

    private static final String FIRST_BENEFITS_ENDPOINT = "/v1/first-benefits";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetFirstBenefitQuery getFirstBenefitQuery;

    @Test
    void first_benefit_목록_조회() throws Exception {
        FirstBenefits firstBenefits = FirstBenefits.of(Arrays.asList(ANY_FIRST_BENEFIT));
        given(getFirstBenefitQuery.getAllByParams(any(GetFirstBenefitParams.class))).willReturn(firstBenefits);

        mockMvc.perform(get(FIRST_BENEFITS_ENDPOINT))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(ANY_FIRST_BENEFIT_ID.getId()));

        then(getFirstBenefitQuery).should()
                .getAllByParams(any(GetFirstBenefitParams.class));
    }

    @Test
    void 유저의_first_benefit_목록_조회() throws Exception {
        FirstBenefits firstBenefits = FirstBenefits.of(Arrays.asList(ANY_FIRST_BENEFIT));
        given(getFirstBenefitQuery.getAllByParams(any(GetFirstBenefitParams.class))).willReturn(firstBenefits);
        String userId = ANY_USER_ID.getId();

        mockMvc.perform(get(FIRST_BENEFITS_ENDPOINT)
                        .param("userId", userId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(ANY_FIRST_BENEFIT_ID.getId()))
                .andExpect(jsonPath("$.data[0].userId").value(userId));

        then(getFirstBenefitQuery).should()
                .getAllByParams(argThat(params -> params.getUserId().equals(userId)));
    }

    @Test
    public void 데이터가_존재하지_않으면_404로_응답한다() throws Exception {
        FirstBenefits firstBenefits = FirstBenefits.of(new ArrayList<>());
        given(getFirstBenefitQuery.getAllByParams(any(GetFirstBenefitParams.class))).willReturn(firstBenefits);

        mockMvc.perform(get(FIRST_BENEFITS_ENDPOINT))
                .andExpect(status().isNotFound());

        then(getFirstBenefitQuery).should()
                .getAllByParams(any(GetFirstBenefitParams.class));
    }
}