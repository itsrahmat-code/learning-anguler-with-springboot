package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.Customer;
import com.emranhss.merchandise.entity.DueList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DueListRepo extends JpaRepository<DueList,Long> {
}
