package com.ys.order.domain.core;

import lombok.Value;

@Value(staticConstructor = "of")
public class OrderId {

    String id;
}
