package com.ys.secondbenefit.adapter.out;

import com.ys.infra.exception.BadRequestException;
import com.ys.secondbenefit.application.port.out.LoadOrderWebPort;
import com.ys.secondbenefit.application.port.out.ResponseModel;
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

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new BadRequestException("Order를 찾을 수 없습니다.");

        }

        return response.getBody().getData();
    }
}
