package com.ys.secondbenefit.application.service;

public interface CommandFactory<R, C> {

    C create(R request);
}
