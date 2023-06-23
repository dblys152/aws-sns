package com.ys.firstbenefit.application.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderCompletedEvent {

    @NotNull
    private String orderId;

    @JsonCreator
    public OrderCompletedEvent(@JsonProperty("orderId") String orderId) {
        this.orderId = orderId;
    }
}
