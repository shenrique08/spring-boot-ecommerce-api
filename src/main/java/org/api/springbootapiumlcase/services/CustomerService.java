package org.api.springbootapiumlcase.services;


import jakarta.persistence.EntityNotFoundException;
import org.api.springbootapiumlcase.domain.Customer;
import org.api.springbootapiumlcase.dto.CustomerDTO;
import org.api.springbootapiumlcase.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id:"  + id));
    }

    public List<CustomerDTO> findAll() {
        List<Customer> customers = repository.findAll();
        return customers.stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    public Customer insert(Customer obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public void update(Customer obj) {
        Customer newObj = findById(obj.getId());
        updateData(newObj, obj);
        repository.save(newObj);
    }

    public void deleteById(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Cannot delete a customer that has associated orders.");
        }
    }

    public Customer fromDTO(CustomerDTO objDTO) {
        return Customer.builder()
                .id(objDTO.getId())
                .name(objDTO.getName())
                .email(objDTO.getEmail())
                .build();
    }

    private void updateData(Customer newObj, Customer obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
