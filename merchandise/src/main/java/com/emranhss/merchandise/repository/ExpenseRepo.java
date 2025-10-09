package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {

//
//    @Query("SELECT COALESCE(SUM(i.total), 0) " +
//            "FROM Expense i " +
//            "WHERE i.date BETWEEN :start AND :end")
//    Double getExpenseBetween(@Param("start") LocalDateTime start,
//                          @Param("end") LocalDateTime end);
//
}
