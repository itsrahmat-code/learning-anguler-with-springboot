package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.DueList;
import com.emranhss.merchandise.service.DueListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/duelist")
@CrossOrigin("*")
public class DueListController {

    @Autowired
    private DueListService dueListService;

    // ✅ Read all due records
    @GetMapping("")
    public List<DueList> getAllDueList() {
        return dueListService.getAllDueList();
    }

    // ✅ Read one by ID
    @GetMapping("/{id}")
    public Optional<DueList> getDueListById(@PathVariable Long id) {
        return dueListService.getDueListById(id);
    }

    // ✅ Delete by ID
    @DeleteMapping("/{id}")
    public void deleteDueList(@PathVariable Long id) {
        dueListService.deleteDueList(id);
    }
}
