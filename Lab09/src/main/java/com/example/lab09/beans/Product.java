package com.example.lab09.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer productId;

    @Column(name = "ProductName", nullable = false, length = 40)
    private String productName;

    @Column(name = "SupplierID")
    private Integer supplierId;

    @Column(name = "CategoryID")
    private Integer categoryId;

    @Column(name = "Unit", length = 50)
    private String unit;

    @Column(name = "Price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "CategoryID", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "SupplierID", insertable = false, updatable = false)
    private Supplier supplier;

    public Product() {
    }

    public Product(String productName, Integer supplierId, Integer categoryId, String unit, Double price) {
        this.productName = productName;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.unit = unit;
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
