package com.ys.firstbenefit.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderModel {

    String id;
    String ordererUserId;
    LocalDateTime completedAt;
}
