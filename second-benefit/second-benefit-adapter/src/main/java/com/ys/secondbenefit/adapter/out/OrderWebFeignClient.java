package com.ys.secondbenefit.adapter.out;

import com.ys.secondbenefit.application.port.out.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", url = "${endpoint.order-v1}")
public interface OrderWebFeignClient {

    @GetMapping("/{orderId}")
    ResponseEntity<ResponseModel<ResponseModel.OrderModel>> get(@PathVariable("orderId") String orderId);
}
