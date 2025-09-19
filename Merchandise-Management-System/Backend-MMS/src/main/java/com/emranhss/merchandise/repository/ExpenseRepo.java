package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {
}
