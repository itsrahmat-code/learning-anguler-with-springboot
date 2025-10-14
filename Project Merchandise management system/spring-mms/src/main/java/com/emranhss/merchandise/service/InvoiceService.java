package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.DueList;
import com.emranhss.merchandise.entity.Invoice;
import com.emranhss.merchandise.entity.Product;
import com.emranhss.merchandise.repository.DueListRepo;
import com.emranhss.merchandise.repository.InvoiceRepo;
import com.emranhss.merchandise.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo invoiceRepo;
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DueListRepo dueListRepo;


    @Transactional
    public Invoice save(Invoice invoice) {
        List<Product> soldProducts = new ArrayList<>();

        for (Product invoiceProduct : invoice.getProducts()) {
            // Fetch stock product
            Product stockProduct = productRepo.findById(invoiceProduct.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + invoiceProduct.getId()));

            // Deduct stock
            int newQuantity = stockProduct.getQuantity() - invoiceProduct.getQuantity();
            if (newQuantity < 0) {
                throw new RuntimeException("Not enough stock for product: " + stockProduct.getName());
            }
            stockProduct.setQuantity(newQuantity);


            //productRepo.save(stockProduct);

            // Create snapshot product for invoice
            Product sold = new Product();
            sold.setName(stockProduct.getName());
            sold.setCategory(stockProduct.getCategory());
            sold.setBrand(stockProduct.getBrand());
            sold.setModel(stockProduct.getModel());
            sold.setDetails(stockProduct.getDetails());
            sold.setPrice(stockProduct.getPrice());
            sold.setQuantity(invoiceProduct.getQuantity()); // sold qty
            sold.setInvoice(invoice);

            soldProducts.add(sold);
        }

        invoice.setProducts(soldProducts);

        // Set defaults if not provided
        if (invoice.getDate() == null) {
            invoice.setDate(LocalDateTime.now());
        }
        if (invoice.getInvoiceNumber() == null) {
            invoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
        }

        invoice.calculateTotals();

        Invoice savedInvoice = invoiceRepo.save(invoice);

        // ✅ Save into DueList
        if (savedInvoice.getDue() > 0) {
            DueList dueEntry = new DueList();
            dueEntry.setInvoiceNumber(savedInvoice.getInvoiceNumber());
            dueEntry.setDate(java.sql.Timestamp.valueOf(savedInvoice.getDate()));
            dueEntry.setCustomerName(savedInvoice.getName());
            dueEntry.setTotalAmount(savedInvoice.getTotal());
            dueEntry.setDue(savedInvoice.getDue());
            dueEntry.setPayment(savedInvoice.getPaid());

            dueListRepo.save(dueEntry);
        }

        return savedInvoice;
    }


    public List<Invoice> getAll() {
        return invoiceRepo.findAll();
    }

    public Optional<Invoice> getById(Long id) {
        return invoiceRepo.findById(id);
    }

    public void delete(Long id) {
        invoiceRepo.deleteById(id);
    }

}
