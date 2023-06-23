package com.ys.firstbenefit.adapter.out;

import com.ys.firstbenefit.application.exception.OrderNotFoundException;
import com.ys.firstbenefit.application.port.out.LoadOrderWebPort;
import com.ys.firstbenefit.application.port.out.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderWebAdapter implements LoadOrderWebPort {

    private final OrderWebFeignClient orderWebFeignClient;

    @Override
    public ResponseModel.OrderModel get(String orderId) {
        ResponseEntity<ResponseModel<ResponseModel.OrderModel>> response
                = orderWebFeignClient.get(orderId);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody().getData();
        } else {
            throw new OrderNotFoundException("Order를 찾을 수 없습니다.");
        }
    }
}
