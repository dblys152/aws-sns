package com.ys.secondbenefit.application.service;

import com.ys.secondbenefit.application.port.in.GetSecondBenefitParams;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitQuery;
import com.ys.secondbenefit.application.port.out.LoadSecondBenefitPort;
import com.ys.secondbenefit.domain.SecondBenefits;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecondBenefitQueryService implements GetSecondBenefitQuery {

    private final LoadSecondBenefitPort loadSecondBenefitPort;

    @Override
    public SecondBenefits getAllByParams(GetSecondBenefitParams params) {
        return loadSecondBenefitPort.findAllByParams(params);
    }
}
