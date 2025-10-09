package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.ReplaceUnit;
import com.emranhss.merchandise.repository.ReplaceUnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplaceUnitService {

    @Autowired
    private ReplaceUnitRepo replaceUnitRepo;

    public ReplaceUnit save(ReplaceUnit replaceUnit) {
        return replaceUnitRepo.save(replaceUnit);
    }

    public List<ReplaceUnit> getAllReplaceUnits() {
        return replaceUnitRepo.findAll();
    }

    public Optional<ReplaceUnit> getById(Long id) {
        return replaceUnitRepo.findById(id);
    }

    public ReplaceUnit update(Long id, ReplaceUnit updatedReplaceUnit) {
        return replaceUnitRepo.findById(id).map(existing -> {
            existing.setName(updatedReplaceUnit.getName());
            existing.setDetails(updatedReplaceUnit.getDetails());
            existing.setQty(updatedReplaceUnit.getQty());
            existing.setPrice(updatedReplaceUnit.getPrice());
            return replaceUnitRepo.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        replaceUnitRepo.deleteById(id);
    }
}
