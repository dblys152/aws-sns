package com.ys.order.adapter.in;

import com.ys.order.application.port.in.GetOrderQuery;
import com.ys.order.domain.core.Order;
import com.ys.order.domain.core.OrderId;
import com.ys.refs.user.domain.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = OrderQueryController.class)
class OrderQueryControllerTest {

    private static final String ORDERS_ENDPOINT = "/v1/orders";
    private static final OrderId ANY_ORDER_ID = OrderId.of("ANY_ORDER_ID");
    private static final UserId TEST_USER_ID = UserId.of("testUserId");
    private static final LocalDateTime ANY_COMPLETED_AT = LocalDateTime.now();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetOrderQuery getOrderQuery;

    @Test
    void 더미_ORDER_조회() throws Exception {
        Order order = Order.of(ANY_ORDER_ID, TEST_USER_ID, ANY_COMPLETED_AT);
        given(getOrderQuery.get(any())).willReturn(order);

        mockMvc.perform(get(ORDERS_ENDPOINT+ "/{orderId}", ANY_ORDER_ID))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value(ANY_ORDER_ID.getId()))
                .andExpect(jsonPath("$.data.ordererUserId").value(TEST_USER_ID.getId()));

        then(getOrderQuery).should()
                .get(any());
    }
}