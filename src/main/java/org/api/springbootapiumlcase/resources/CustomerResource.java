package org.api.springbootapiumlcase.resources;

import org.api.springbootapiumlcase.domain.Customer;
import org.api.springbootapiumlcase.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    private final CustomerService service;

    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer customer = service.findById(id);
        return ResponseEntity.ok().body(customer);
    }
}
