package com.ys.firstbenefit.application.service;

public interface CommandFactory<R, C> {

    C create(R request);
}
