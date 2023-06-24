package com.ys.firstbenefit.application.service;

import com.ys.firstbenefit.application.port.in.GetFirstBenefitQuery;
import com.ys.firstbenefit.application.port.out.LoadFirstBenefitPort;
import com.ys.firstbenefit.domain.FirstBenefits;
import com.ys.refs.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirstBenefitQueryService implements GetFirstBenefitQuery {

    private final LoadFirstBenefitPort loadFirstBenefitPort;

    @Override
    public FirstBenefits getAllByUserId(UserId userId) {
        return loadFirstBenefitPort.findAllByUserId(userId);
    }
}
