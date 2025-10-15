package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.DueList;
import com.emranhss.merchandise.repository.DueListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DueListService {

    @Autowired
    private DueListRepo dueListRepo;

    // ✅ Get all due list entries
    public List<DueList> getAllDueList() {
        return dueListRepo.findAll();
    }

    // ✅ Get a single due entry by ID
    public Optional<DueList> getDueListById(Long id) {
        return dueListRepo.findById(id);
    }

    // ✅ Delete a due entry by ID
    public void deleteDueList(Long id) {
        dueListRepo.deleteById(id);
    }



    // DUEdashboard

    public Double getLast30DaysDue() {
        LocalDateTime start = LocalDate.now().minusDays(30).atStartOfDay();
        LocalDateTime end = LocalDateTime.now();
        return dueListRepo.getDuesBetween(start, end);
    }

    // DUEdashboard end



}

