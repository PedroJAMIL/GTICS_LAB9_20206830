package com.example.cliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    
    private Integer productId;
    private String productName;
    private Integer supplierId;
    private Integer categoryId;
    private String unit;
    private Double price;
    private CategoryDTO category;
    private SupplierDTO supplier;
    
    public ProductDTO() {
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
    
    public CategoryDTO getCategory() {
        return category;
    }
    
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    
    public SupplierDTO getSupplier() {
        return supplier;
    }
    
    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }
}
