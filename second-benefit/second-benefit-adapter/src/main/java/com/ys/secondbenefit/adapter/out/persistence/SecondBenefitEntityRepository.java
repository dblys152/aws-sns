package com.ys.secondbenefit.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecondBenefitEntityRepository extends JpaRepository<SecondBenefitEntity, String> {

    List<SecondBenefitEntity> findAllByUserId(String userId);
}
