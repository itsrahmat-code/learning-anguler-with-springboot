package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

@Entity
public class ReInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private String date;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String productdetail;
    private int productqty;
    private double price;
    private double discount;
    private double paid;
    private double total;
    private double due;
    private String createdBy;

    public ReInvoice() {
    }

    public ReInvoice(Long id, String invoiceNumber, String date, String name, String phone, String email,
                     String address, String productdetail, int productqty, double price, double discount,
                     double paid, double total, double due, String createdBy) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.productdetail = productdetail;
        this.productqty = productqty;
        this.price = price;
        this.discount = discount;
        this.paid = paid;
        this.total = total;
        this.due = due;
        this.createdBy = createdBy;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductdetail() {
        return productdetail;
    }

    public void setProductdetail(String productdetail) {
        this.productdetail = productdetail;
    }

    public int getProductqty() {
        return productqty;
    }

    public void setProductqty(int productqty) {
        this.productqty = productqty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
