package com.ys.firstbenefit.application.service;

import com.ys.firstbenefit.application.port.in.GetFirstBenefitParams;
import com.ys.firstbenefit.application.port.in.GetFirstBenefitQuery;
import com.ys.firstbenefit.application.port.out.LoadFirstBenefitPort;
import com.ys.firstbenefit.domain.FirstBenefits;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirstBenefitQueryService implements GetFirstBenefitQuery {

    private final LoadFirstBenefitPort loadFirstBenefitPort;

    @Override
    public FirstBenefits getAllByParams(GetFirstBenefitParams params) {
        return loadFirstBenefitPort.findAllByParams(params);
    }

}
