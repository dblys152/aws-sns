package com.ys.firstbenefit.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirstBenefitEntityRepository extends JpaRepository<FirstBenefitEntity, String> {

    List<FirstBenefitEntity> findAllByUserId(String userId);
}
