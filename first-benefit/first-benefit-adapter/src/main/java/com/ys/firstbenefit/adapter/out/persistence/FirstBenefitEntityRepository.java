package com.ys.firstbenefit.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstBenefitEntityRepository extends JpaRepository<FirstBenefitEntity, String> {

}
