package com.example.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}