package com.ys.firstbenefit.adapter.out;

import com.ys.firstbenefit.adapter.out.persistence.FirstBenefitEntity;
import com.ys.firstbenefit.adapter.out.persistence.FirstBenefitEntityRepository;
import com.ys.firstbenefit.adapter.out.persistence.FirstBenefitEntityRepositorySupport;
import com.ys.firstbenefit.application.port.in.GetFirstBenefitParams;
import com.ys.firstbenefit.application.port.out.LoadFirstBenefitPort;
import com.ys.firstbenefit.application.port.out.RecordFirstBenefitPort;
import com.ys.firstbenefit.domain.FirstBenefit;
import com.ys.firstbenefit.domain.FirstBenefits;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FirstBenefitPersistenceAdapter implements RecordFirstBenefitPort, LoadFirstBenefitPort {

    private final FirstBenefitEntityRepository repository;
    private final FirstBenefitEntityRepositorySupport repositorySupport;

    @Override
    public FirstBenefit save(FirstBenefit firstBenefit) {
        FirstBenefitEntity entity = repository.save(FirstBenefitEntity.fromDomain(firstBenefit));
        return entity.toDomain();
    }

    @Override
    public FirstBenefits findAllByParams(GetFirstBenefitParams params) {
        List<FirstBenefitEntity> firstBenefitEntityList = repositorySupport.findAllByParams(params);
        return FirstBenefits.of(firstBenefitEntityList.stream()
                .map(f -> f.toDomain())
                .toList());
    }
}
