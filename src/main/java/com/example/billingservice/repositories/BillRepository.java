package com.example.billingservice.repositories;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill , Long> { }
@Projection(name = "fullBill" , types = Bill.class)
interface BillProjection{
    public Long getId();
    public Date getBillingDate();
    public Long getCustomerID();
    public Collection<ProductItem> getProductItems();
}