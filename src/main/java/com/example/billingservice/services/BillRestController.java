package com.example.billingservice.services;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InventoryService inventoryService;
    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") long id){
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill not found"));
        bill.setCustomer(customerService.findCustomerById(bill.getCustomerID()));
        if (bill.getProductItems() != null) bill.getProductItems().forEach(pi ->{
            pi.setProduct(inventoryService.findProductById(pi.getProductID()));
        });
        return bill;
    }
}
