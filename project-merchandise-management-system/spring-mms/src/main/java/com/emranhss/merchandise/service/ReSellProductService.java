package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.ReSellProduct;
import com.emranhss.merchandise.repository.ReSellproductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReSellProductService {

    @Autowired
    private ReSellproductRepo reSellproductRepo;

    public ReSellProduct save(ReSellProduct reSellProduct) {
        return reSellproductRepo.save(reSellProduct);
    }

    public List<ReSellProduct> getAllReSellProducts() {
        return reSellproductRepo.findAll();
    }

    public Optional<ReSellProduct> getById(Long id) {
        return reSellproductRepo.findById(id);
    }

    public ReSellProduct update(Long id, ReSellProduct updatedReSellProduct) {
        return reSellproductRepo.findById(id).map(existing -> {
            existing.setName(updatedReSellProduct.getName());
            existing.setDetails(updatedReSellProduct.getDetails());
            existing.setQty(updatedReSellProduct.getQty());
            existing.setPrice(updatedReSellProduct.getPrice());
            return reSellproductRepo.save(existing);
        }).orElse(null);
    }


    public void delete(Long id) {
        reSellproductRepo.deleteById(id);
    }
}