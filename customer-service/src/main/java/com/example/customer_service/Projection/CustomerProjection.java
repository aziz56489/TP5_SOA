package com.example.customer_service.Projection;

import com.example.customer_service.entity.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCustomer" , types = Customer.class)
public interface CustomerProjection {
    public Long getId();
    public String getName();
    public String getEamil();
}
