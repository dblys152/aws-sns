package com.ys.firstbenefit.adapter.out;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.ys.firstbenefit.application.port.out.ResponseModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderWebFeignClientTest {

    private static final String ANY_ORDER_ID = "ANY_ORDER_ID";

    private static WireMockServer wireMockServer;

    @LocalServerPort
    private int port;

    @Autowired
    private OrderWebFeignClient orderWebFeignClient;

    @BeforeAll
    static void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().dynamicPort());
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());
    }

    @AfterAll
    static void teardown() {
        wireMockServer.stop();
    }

    @Test
    void 주문_정보를_조회한다() {
        wireMockServer.stubFor(get(urlPathEqualTo("/order-service/" + ANY_ORDER_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"status\":\"success\", \"data\":{\"orderId\":\""+ ANY_ORDER_ID +"\"}}")));

        ResponseEntity<ResponseModel<ResponseModel.OrderModel>> actual
                = orderWebFeignClient.get(ANY_ORDER_ID);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseModel<ResponseModel.OrderModel> responseModel = actual.getBody();
        assertThat(responseModel).isNotNull();
        assertThat(responseModel.getData().getId()).isEqualTo(ANY_ORDER_ID);
    }
}