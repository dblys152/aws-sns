package com.ys.firstbenefit.adapter.in;

import com.ys.firstbenefit.application.port.in.GetFirstBenefitQuery;
import com.ys.firstbenefit.domain.FirstBenefits;
import com.ys.refs.user.domain.UserId;
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

@WebMvcTest(controllers = FirstBenefitQueryController.class)
class FirstBenefitQueryControllerTest {

    private static final String ANY_USER_ID = "ANY_USER_ID";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetFirstBenefitQuery getFirstBenefitQuery;

    @Test
    void get_first_benefits() throws Exception {
        FirstBenefits firstBenefits = FirstBenefits.of(new ArrayList<>());
        given(getFirstBenefitQuery.getAllByUserId(UserId.of(ANY_USER_ID))).willReturn(firstBenefits);

        mockMvc.perform(get("/v1/first-benefits/{userId}", ANY_USER_ID))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        then(getFirstBenefitQuery).should()
                .getAllByUserId(UserId.of(ANY_USER_ID));
    }
}