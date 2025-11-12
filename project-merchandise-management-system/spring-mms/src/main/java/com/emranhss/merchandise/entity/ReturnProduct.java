package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="ReturnProduct")
public class ReturnProduct {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private  String invoice;
    // Indicates if it's a return or damage (you could also use an enum)
    private String type; // "RETURN" or "DAMAGE"
    private int quantity;
    private Date date;
    @Column(length = 500)
    private String reason;
    private String productName;
    private String photo;
    private Double price; // ✅ Ensure this exists
    private String status; // ✅ Add this line
    public ReturnProduct() {
    }

    public ReturnProduct(Long id, String invoice, String type, int quantity, Date date, String reason, String productName, String photo, Double price, String status) {
        this.id = id;
        this.invoice = invoice;
        this.type = type;
        this.quantity = quantity;
        this.date = date;
        this.reason = reason;
        this.productName = productName;
        this.photo = photo;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // ✅ ADD THESE METHODS
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
