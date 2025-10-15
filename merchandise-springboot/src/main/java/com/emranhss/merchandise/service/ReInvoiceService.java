package com.emranhss.merchandise.service;


import com.emranhss.merchandise.entity.ReInvoice;
import com.emranhss.merchandise.repository.ReInvoiceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReInvoiceService {

        @Autowired
        private ReInvoiceRepo reInvoiceRepo;

        // ✅ Create
        public ReInvoice save(ReInvoice reInvoice) {
            return reInvoiceRepo.save(reInvoice);
        }

        // ✅ Read All
        public List<ReInvoice> getAll() {
            return reInvoiceRepo.findAll();
        }

        // ✅ Read by ID
        public Optional<ReInvoice> getById(Long id) {
            return reInvoiceRepo.findById(id);
        }

        // ✅ Update
        public ReInvoice update(Long id, ReInvoice updatedReInvoice) {
            return reInvoiceRepo.findById(id)
                    .map(existing -> {
                        // Manually update fields (customize this based on your entity)
                        existing.setInvoiceNumber(updatedReInvoice.getInvoiceNumber());
                        existing.setDate(updatedReInvoice.getDate());
                        existing.setName(updatedReInvoice.getName());
                        existing.setPhone(updatedReInvoice.getPhone());
                        existing.setEmail(updatedReInvoice.getEmail());
                        existing.setAddress(updatedReInvoice.getAddress());
                        existing.setProductdetail(updatedReInvoice.getProductdetail());
                        existing.setProductqty(updatedReInvoice.getProductqty());
                        existing.setPrice(updatedReInvoice.getPrice());
                        existing.setDiscount(updatedReInvoice.getDiscount());
                        existing.setPaid(updatedReInvoice.getPaid());
                        existing.setDue(updatedReInvoice.getDue());
                        existing.setTotal(updatedReInvoice.getTotal());
                        existing.setCreatedBy(updatedReInvoice.getCreatedBy());
                        return reInvoiceRepo.save(existing);
                    })
                    .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));
        }

        // ✅ Delete
        public void delete(Long id) {
            reInvoiceRepo.deleteById(id);
        }
    }
