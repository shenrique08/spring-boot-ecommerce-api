package org.api.springbootapiumlcase.services;


import jakarta.persistence.EntityNotFoundException;
import org.api.springbootapiumlcase.domain.Customer;
import org.api.springbootapiumlcase.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;


    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id:"  + id));
    }
}
