package com.emranhss.merchandise.RestController;
import com.emranhss.merchandise.entity.ReInvoice;

import com.emranhss.merchandise.service.ReInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reinvoices")
@CrossOrigin("*")
public class ReinvoiceController {

        @Autowired
        private ReInvoiceService reInvoiceService;

        // ✅ Create
        @PostMapping("/add")
        public ReInvoice createReInvoice(@RequestBody ReInvoice reInvoice) {
            return reInvoiceService.save(reInvoice);
        }

        // ✅ Read all
        @GetMapping
        public List<ReInvoice> getAllReinvoices() {
            return reInvoiceService.getAll();
        }

        // ✅ Read by ID
        @GetMapping("/{id}")
        public Optional<ReInvoice> getReinvoiceById(@PathVariable Long id) {
            return reInvoiceService.getById(id);
        }

        // ✅ Update
        @PutMapping("/update/{id}")
        public ReInvoice updateReinvoice(@PathVariable Long id, @RequestBody ReInvoice updatedReInvoice) {
            return reInvoiceService.update(id, updatedReInvoice);
        }

        // ✅ Delete
        @DeleteMapping("/delete/{id}")
        public void deleteReinvoice(@PathVariable Long id) {
            reInvoiceService.delete(id);
        }
    }
