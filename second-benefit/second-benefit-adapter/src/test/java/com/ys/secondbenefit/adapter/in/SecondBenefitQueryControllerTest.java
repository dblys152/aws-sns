package com.ys.secondbenefit.adapter.in;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitQuery;
import com.ys.secondbenefit.domain.SecondBenefits;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SecondBenefitQueryController.class)
class SecondBenefitQueryControllerTest {

    private static final String ANY_USER_ID = "ANY_USER_ID";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetSecondBenefitQuery getSecondBenefitQuery;

    @Test
    void get_second_benefits() throws Exception {
        SecondBenefits firstBenefits = SecondBenefits.of(new ArrayList<>());
        given(getSecondBenefitQuery.getAllByUserId(UserId.of(ANY_USER_ID))).willReturn(firstBenefits);

        mockMvc.perform(get("/v1/first-benefits/{userId}", ANY_USER_ID))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        then(getSecondBenefitQuery).should()
                .getAllByUserId(UserId.of(ANY_USER_ID));
    }
}