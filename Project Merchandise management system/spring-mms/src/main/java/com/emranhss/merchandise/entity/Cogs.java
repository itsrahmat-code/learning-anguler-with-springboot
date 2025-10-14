package com.emranhss.merchandise.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "cogs")
public class Cogs {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String productName;

        @Column(nullable = false)
        private String purchaseInvoice;

        @Column(nullable = false)
        private Integer productQty; // Quantity of product purchased

        @Column(nullable = false)
        private Double productCost; // Raw/base product cost (not per unit)

        @Column(nullable = false)
        private Double transportFee;

        @Column(nullable = false)
        private Double labourCost;

        @Column(nullable = false)
        private Double packingCost;

        @Column(nullable = false)
        private Double tax;

        @Column(nullable = false)
        private Double totalCogs; // Sum of all above costs

        @Column(nullable = false)
        private Double eachProductPrice; // totalCogs / productQty

        @Temporal(TemporalType.DATE)
        @Column(nullable = false)
        private Date date;

        // Default constructor
        public Cogs() {}

        // All-args constructor
        public Cogs(Long id, String productName, String purchaseInvoice, Integer productQty,
                    Double productCost, Double transportFee, Double labourCost,
                    Double packingCost, Double tax, Double totalCogs, Double eachProductPrice,
                    Date date) {
            this.id = id;
            this.productName = productName;
            this.purchaseInvoice = purchaseInvoice;
            this.productQty = productQty;
            this.productCost = productCost;
            this.transportFee = transportFee;
            this.labourCost = labourCost;
            this.packingCost = packingCost;
            this.tax = tax;
            this.totalCogs = totalCogs;
            this.eachProductPrice = eachProductPrice;
            this.date = date;
        }

        // Getters and setters for all fields

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getPurchaseInvoice() {
            return purchaseInvoice;
        }

        public void setPurchaseInvoice(String purchaseInvoice) {
            this.purchaseInvoice = purchaseInvoice;
        }

        public Integer getProductQty() {
            return productQty;
        }

        public void setProductQty(Integer productQty) {
            this.productQty = productQty;
        }

        public Double getProductCost() {
            return productCost;
        }

        public void setProductCost(Double productCost) {
            this.productCost = productCost;
        }

        public Double getTransportFee() {
            return transportFee;
        }

        public void setTransportFee(Double transportFee) {
            this.transportFee = transportFee;
        }

        public Double getLabourCost() {
            return labourCost;
        }

        public void setLabourCost(Double labourCost) {
            this.labourCost = labourCost;
        }

        public Double getPackingCost() {
            return packingCost;
        }

        public void setPackingCost(Double packingCost) {
            this.packingCost = packingCost;
        }

        public Double getTax() {
            return tax;
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public Double getTotalCogs() {
            return totalCogs;
        }

        public void setTotalCogs(Double totalCogs) {
            this.totalCogs = totalCogs;
        }

        public Double getEachProductPrice() {
            return eachProductPrice;
        }

        public void setEachProductPrice(Double eachProductPrice) {
            this.eachProductPrice = eachProductPrice;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
