package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.ReplaceUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplaceUnitRepo extends JpaRepository<ReplaceUnit,Long> {

}
