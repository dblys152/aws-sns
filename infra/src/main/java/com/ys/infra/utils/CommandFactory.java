package com.ys.infra.utils;

public interface CommandFactory<R, C> {

    C create(R request);
}
