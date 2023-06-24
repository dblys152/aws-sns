package com.ys.secondbenefit.application.port.out;

public interface LoadOrderWebPort {

    ResponseModel.OrderModel get(String orderId);
}
