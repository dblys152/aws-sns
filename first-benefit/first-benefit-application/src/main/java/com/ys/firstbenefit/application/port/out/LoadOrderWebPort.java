package com.ys.firstbenefit.application.port.out;

public interface LoadOrderWebPort {

    ResponseModel.OrderModel get(String orderId);
}
