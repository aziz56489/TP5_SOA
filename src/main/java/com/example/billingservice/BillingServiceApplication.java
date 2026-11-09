package com.example.billingservice;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.Customer;
import com.example.billingservice.entities.Product;
import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.repositories.BillRepository;
import com.example.billingservice.repositories.ProductItemRepository;
import com.example.billingservice.services.CustomerService;
import com.example.billingservice.services.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
                            CustomerService customerService, InventoryService inventoryService){
        return args -> {
            if (billRepository.count() > 0) return;
            try {
                Customer c1 = customerService.findCustomerById(1L);
                Bill bill1 = billRepository.save(new Bill(new Date(), c1.getId(), null, null));
                PagedModel<Product> products = inventoryService.findAllProducts();
                products.getContent().forEach(p -> productItemRepository.save(
                        new ProductItem(p.getId(), p, p.getPrice(), 90, bill1)));
            } catch (RuntimeException e) {
                System.err.println("Could not create the demo bill because customer or inventory service is unavailable: " + e.getMessage());
            }
        };
    }
}
