package com.example.billingservice.services;

import com.example.billingservice.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Inventory-service")
public interface InventoryService {
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable(name="id") Long id);
    @GetMapping("/products")
    public PagedModel<Product> findAllProducts();
}
