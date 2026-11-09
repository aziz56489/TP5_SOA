package com.example.inventoryservice;

import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {
    @Autowired
    ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository , RepositoryRestConfiguration restConfiguration){
        return args->{
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product("PC LENOVO" , 1500));
            productRepository.save(new Product("IMP EPSONI" , 500.5));
            productRepository.save(new Product("CBL HDMI" , 3.5));
            productRepository.findAll().forEach(System.out::println);
        };
    }
}
