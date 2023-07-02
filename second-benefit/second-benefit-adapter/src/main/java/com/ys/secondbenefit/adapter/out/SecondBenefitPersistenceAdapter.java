package com.ys.secondbenefit.adapter.out;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.adapter.out.persistence.SecondBenefitEntity;
import com.ys.secondbenefit.adapter.out.persistence.SecondBenefitEntityRepository;
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

    @Override
    public SecondBenefit save(SecondBenefit secondBenefit) {
        SecondBenefitEntity entity = repository.save(SecondBenefitEntity.fromDomain(secondBenefit));
        return entity.toDomain();
    }

    @Override
    public SecondBenefits findAllByUserId(UserId userId) {
        List<SecondBenefitEntity> secondBenefitEntityList = repository.findAllByUserId(userId.getId());
        return SecondBenefits.of(secondBenefitEntityList.stream()
                .map(f -> f.toDomain())
                .toList());
    }
}