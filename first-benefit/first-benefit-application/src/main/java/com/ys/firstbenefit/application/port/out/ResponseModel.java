package com.ys.firstbenefit.application.port.out;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResponseModel<T> {

    String status;
    T data;

    @Data
    @NoArgsConstructor
    public static class OrderModel {
        String id;
        String ordererUserId;
        LocalDateTime completedAt;
    }
}
