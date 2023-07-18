package com.ys.firstbenefit.application.service;

public interface CommandFactory<R, T> {

    T create(R request);
}
