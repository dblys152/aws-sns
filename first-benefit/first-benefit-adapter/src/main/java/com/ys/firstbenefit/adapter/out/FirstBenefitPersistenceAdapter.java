package com.ys.firstbenefit.adapter.out;

import com.ys.firstbenefit.adapter.out.persistence.FirstBenefitEntity;
import com.ys.firstbenefit.adapter.out.persistence.FirstBenefitEntityRepository;
import com.ys.firstbenefit.application.port.out.RecordFirstBenefitPort;
import com.ys.firstbenefit.domain.FirstBenefit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FirstBenefitPersistenceAdapter implements RecordFirstBenefitPort {

    private final FirstBenefitEntityRepository repository;

    @Override
    public FirstBenefit save(FirstBenefit firstBenefit) {
        FirstBenefitEntity entity = repository.save(FirstBenefitEntity.fromDomain(firstBenefit));
        return entity.toDomain();
    }
}
