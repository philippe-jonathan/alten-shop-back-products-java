package com.example.back.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.back.model.Product;
import com.example.back.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    ProductService productService;

    @GetMapping("")
    public List<Product> list() {
        return productService.listAllProduct();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        try {
            Product product = productService.getProduct(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdate(@RequestBody Product partialUpdate, @PathVariable Integer id) {
        try {
            Product existingProduct = productService.getProduct(id);

            if(partialUpdate.getCode() != null) {
                existingProduct.setCode(partialUpdate.getCode());
            }

            if(partialUpdate.getName() != null) {
                existingProduct.setName(partialUpdate.getName());
            }

            if(partialUpdate.getDescription() != null) {
                existingProduct.setDescription(partialUpdate.getDescription());
            }

            if(partialUpdate.getPrice() != null) {
                existingProduct.setPrice(partialUpdate.getPrice());
            }

            if(partialUpdate.getQuantity() != null) {
                existingProduct.setQuantity(partialUpdate.getQuantity());
            }

            if(partialUpdate.getInventoryStatus() != null) {
                existingProduct.setInventoryStatus(partialUpdate.getInventoryStatus());
            }

            if(partialUpdate.getCategory() != null) {
                existingProduct.setCategory(partialUpdate.getCategory());
            }

            if(partialUpdate.getImage() != null) {
                existingProduct.setImage(partialUpdate.getImage());
            }

            if(partialUpdate.getRating() != null) {
                existingProduct.setRating(partialUpdate.getRating());
            }

            productService.updateProduct(existingProduct);
            return new ResponseEntity<>("Product with ID " + id + " updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating product with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    

}
