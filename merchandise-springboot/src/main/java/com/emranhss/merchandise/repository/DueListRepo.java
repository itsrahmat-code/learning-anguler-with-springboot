package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.Customer;
import com.emranhss.merchandise.entity.DueList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface DueListRepo extends JpaRepository<DueList,Long> {
    // DUEdashboard

    @Query("SELECT COALESCE(SUM(i.total), 0) " +
            "FROM Invoice i " +
            "WHERE i.date BETWEEN :start AND :end")
    Double getDuesBetween(@Param("start") LocalDateTime start,
                          @Param("end") LocalDateTime end);

    // DUEdashboard end
}
