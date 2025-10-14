package com.emranhss.merchandise.dto;

import com.emranhss.merchandise.entity.Invoice;
import com.emranhss.merchandise.entity.Product;

import java.util.stream.Collectors;

public class InvoiceMapper {



    public static InvoiceResponseDTO toDTO(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setDate(invoice.getDate());

        dto.setName(invoice.getName());
        dto.setEmail(invoice.getEmail());
        dto.setPhone(invoice.getPhone());
        dto.setAddress(invoice.getAddress());

        dto.setSubtotal(invoice.getSubtotal());
        dto.setDiscount(invoice.getDiscount());
        dto.setTaxRate(invoice.getTaxRate());
        dto.setTaxAmount(invoice.getTaxAmount());
        dto.setTotal(invoice.getTotal());
        dto.setPaid(invoice.getPaid());
        dto.setDue(invoice.getDue());

        dto.setProducts(invoice.getProducts()
                .stream()
                .map(InvoiceMapper::toProductDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    private static ProductResponseDTO toProductDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());   // if you want null → just remove this line
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setBrand(product.getBrand());
        dto.setModel(product.getModel());
        dto.setDetails(product.getDetails());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
        return dto;
    }



}
