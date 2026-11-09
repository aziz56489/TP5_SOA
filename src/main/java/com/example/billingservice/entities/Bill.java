package com.example.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date billingDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long customerID;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    public Bill(Date billingDate, long customerID, Customer customer, Collection<ProductItem> productItems) {
        super();
        this.billingDate = billingDate;
        this.customerID = customerID;
        this.customer = customer;
        this.productItems = productItems;
    }
    public Bill() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(Collection<ProductItem> productItems) {
        this.productItems = productItems;
    }
}
