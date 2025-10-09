package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Temporal(TemporalType.DATE)
        private Date date;

        @Column(nullable = false)
        private String title;

        @Column(length = 1000)
        private String description;

        @Column(nullable = false)
        private double amount;

        private double paid;

        private double due;

        private String paymentMethod;  // e.g., Cash, Bank Transfer, bKash

        private String addedBy;


    public Expense() {
    }

    public Expense(Long id, Date date, String title, String description, double amount, double paid, double due, String paymentMethod, String addedBy) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.paid = paid;
        this.due = due;
        this.paymentMethod = paymentMethod;
        this.addedBy = addedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
}
