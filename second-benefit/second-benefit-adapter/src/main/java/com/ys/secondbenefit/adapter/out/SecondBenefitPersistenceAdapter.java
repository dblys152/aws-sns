package com.ys.secondbenefit.adapter.out;

import com.ys.secondbenefit.adapter.out.persistence.SecondBenefitEntity;
import com.ys.secondbenefit.adapter.out.persistence.SecondBenefitEntityRepository;
import com.ys.secondbenefit.adapter.out.persistence.SecondBenefitEntityRepositorySupport;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitParams;
import com.ys.secondbenefit.application.port.out.LoadSecondBenefitPort;
import com.ys.secondbenefit.application.port.out.RecordSecondBenefitPort;
import com.ys.secondbenefit.domain.SecondBenefit;
import com.ys.secondbenefit.domain.SecondBenefits;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecondBenefitPersistenceAdapter implements RecordSecondBenefitPort, LoadSecondBenefitPort {

    private final SecondBenefitEntityRepository repository;
    private final SecondBenefitEntityRepositorySupport repositorySupport;

    @Override
    public SecondBenefit save(SecondBenefit secondBenefit) {
        SecondBenefitEntity entity = repository.save(SecondBenefitEntity.fromDomain(secondBenefit));
        return entity.toDomain();
    }

    @Override
    public SecondBenefits findAllByParams(GetSecondBenefitParams params) {
        List<SecondBenefitEntity> secondBenefitEntityList = repositorySupport.findAllByParams(params);
        return SecondBenefits.of(secondBenefitEntityList.stream()
                .map(f -> f.toDomain())
                .toList());
    }
}
