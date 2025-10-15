package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class  ReSellProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String details;
    private int qty;
    private double price;


    public ReSellProduct() {
    }

    public ReSellProduct(Long id, String name, String details, int qty, double price) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.qty = qty;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
