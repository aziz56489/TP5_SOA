package com.example.customer_service.Repository;

import com.example.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer , Long> {
    public Customer findCustomerByName(String name);
}
