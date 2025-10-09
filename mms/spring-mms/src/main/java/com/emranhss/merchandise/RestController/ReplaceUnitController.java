package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.ReplaceUnit;
import com.emranhss.merchandise.service.ReplaceUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replaceUnit")
@CrossOrigin("*")
public class ReplaceUnitController {

    @Autowired
    private ReplaceUnitService replaceUnitService;

    @PostMapping("/add")
    public ReplaceUnit createReplaceUnit(@RequestBody ReplaceUnit replaceUnit) {
        return replaceUnitService.save(replaceUnit);
    }

    @GetMapping
    public List<ReplaceUnit> getAllReplaceUnits() {
        return replaceUnitService.getAllReplaceUnits();
    }

    @GetMapping("/{id}")
    public ReplaceUnit getReplaceUnitById(@PathVariable Long id) {
        return replaceUnitService.getById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ReplaceUnit updateReplaceUnit(@PathVariable Long id, @RequestBody ReplaceUnit updatedReplaceUnit) {
        return replaceUnitService.update(id, updatedReplaceUnit);
    }

    @DeleteMapping("/{id}")
    public void deleteReplaceUnit(@PathVariable Long id) {
        replaceUnitService.delete(id);
    }
}
