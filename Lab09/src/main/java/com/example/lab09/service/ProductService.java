package com.example.lab09.service;

import com.example.lab09.beans.Product;
import com.example.lab09.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product productDetails) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setProductName(productDetails.getProductName());
            product.setSupplierId(productDetails.getSupplierId());
            product.setCategoryId(productDetails.getCategoryId());
            product.setUnit(productDetails.getUnit());
            product.setPrice(productDetails.getPrice());
            return productRepository.save(product);
        }
        return null;
    }


    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
