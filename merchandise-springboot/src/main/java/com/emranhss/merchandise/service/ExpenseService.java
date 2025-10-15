package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.Expense;
import com.emranhss.merchandise.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        return expenseRepository.findById(id).map(expense -> {
            expense.setDate(updatedExpense.getDate());
            expense.setTitle(updatedExpense.getTitle());
            expense.setDescription(updatedExpense.getDescription());
            expense.setAmount(updatedExpense.getAmount());
            expense.setPaid(updatedExpense.getPaid());
            expense.setDue(updatedExpense.getDue());
            expense.setPaymentMethod(updatedExpense.getPaymentMethod());
            expense.setAddedBy(updatedExpense.getAddedBy());
            return expenseRepository.save(expense);
        }).orElse(null);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}



// expance
//public Double getTodayExp() {
//    LocalDateTime start = LocalDate.now().atStartOfDay();
//    LocalDateTime end = LocalDateTime.now();
//    return expenseRepository.getSalesBetween(start, end);
//}
//
//public Double getLast7DaysExp() {
//    LocalDateTime start = LocalDate.now().minusDays(7).atStartOfDay();
//    LocalDateTime end = LocalDateTime.now();
//    return expenseRepository.getSalesBetween(start, end);
//}
//
//public Double getLast30DaysExp() {
//    LocalDateTime start = LocalDate.now().minusDays(30).atStartOfDay();
//    LocalDateTime end = LocalDateTime.now();
//    return expenseRepository.getSalesBetween(start, end);
//}
