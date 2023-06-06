package com.ys.order.domain.core;

import com.ys.order.domain.event.OrderCompletedEvent;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class Order {

    @NotNull
    OrderId id;
    @NotNull
    String userId;
    @NotNull
    LocalDateTime completedAt;
}
