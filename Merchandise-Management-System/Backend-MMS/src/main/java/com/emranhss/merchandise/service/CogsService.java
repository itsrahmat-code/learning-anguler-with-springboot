package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.Cogs;
import com.emranhss.merchandise.entity.Customer;
import com.emranhss.merchandise.repository.CogsRepo;
import com.emranhss.merchandise.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CogsService {

        @Autowired
        private CogsRepo cogsRepo;

        // Get all COGS records
        public List<Cogs> getAllCogs() {
            return cogsRepo.findAll();
        }

        // Get a single COGS record by ID
        public Cogs getCogsById(Long id) {
            return cogsRepo.findById(id).orElse(null);
        }

        // Create new COGS record with calculated fields
        public Cogs createCogs(Cogs cogs) {
            calculateCogsFields(cogs);

            if (cogs.getDate() == null) {
                cogs.setDate(new Date()); // default to current date
            }

            return cogsRepo.save(cogs);
        }

        // Update an existing COGS record
        public Cogs updateCogs(Long id, Cogs updatedCogs) {
            return cogsRepo.findById(id).map(existingCogs -> {
                existingCogs.setProductName(updatedCogs.getProductName());
                existingCogs.setPurchaseInvoice(updatedCogs.getPurchaseInvoice());
                existingCogs.setProductQty(updatedCogs.getProductQty());
                existingCogs.setProductCost(updatedCogs.getProductCost());
                existingCogs.setTransportFee(updatedCogs.getTransportFee());
                existingCogs.setLabourCost(updatedCogs.getLabourCost());
                existingCogs.setPackingCost(updatedCogs.getPackingCost());
                existingCogs.setTax(updatedCogs.getTax());
                existingCogs.setDate(updatedCogs.getDate());

                // Recalculate totals
                calculateCogsFields(existingCogs);

                return cogsRepo.save(existingCogs);
            }).orElse(null);
        }

        // Delete a COGS record by ID
        public void deleteCogs(Long id) {
            cogsRepo.deleteById(id);
        }

        // --- Helper method to calculate totalCOGS and eachProductPrice ---
        private void calculateCogsFields(Cogs cogs) {
            double totalCogs = cogs.getProductCost()
                    + cogs.getTransportFee()
                    + cogs.getLabourCost()
                    + cogs.getPackingCost()
                    + cogs.getTax();

            double eachProductPrice = (cogs.getProductQty() != null && cogs.getProductQty() > 0)
                    ? totalCogs / cogs.getProductQty()
                    : 0.0;

            cogs.setTotalCogs(totalCogs);
            cogs.setEachProductPrice(eachProductPrice);
        }
    }
