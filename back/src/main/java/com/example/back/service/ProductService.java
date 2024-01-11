package com.example.back.service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.model.Product;
import com.example.back.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        Instant currentInstant = Instant.now().plusSeconds(3600); // Ajoute 1 heure (3600 secondes)
        product.setCreatedAt(new java.sql.Timestamp(currentInstant.toEpochMilli()));
        product.setUpdatedAt(new java.sql.Timestamp(currentInstant.toEpochMilli()));



        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        Instant currentInstant = Instant.now().plusSeconds(3600); // Ajoute 1 heure (3600 secondes)
        product.setUpdatedAt(new java.sql.Timestamp(currentInstant.toEpochMilli()));
        productRepository.save(product);
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}
