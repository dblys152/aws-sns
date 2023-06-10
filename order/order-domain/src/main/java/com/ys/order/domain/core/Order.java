package com.ys.order.domain.core;

import com.ys.refs.user.domain.UserId;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class Order {

    @NotNull
    OrderId id;
    @NotNull
    UserId ordererUserId;
    @NotNull
    LocalDateTime completedAt;
}
