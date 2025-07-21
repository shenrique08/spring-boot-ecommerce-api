package org.api.springbootapiumlcase.services;

import jakarta.persistence.EntityNotFoundException;
import org.api.springbootapiumlcase.domain.Order;
import org.api.springbootapiumlcase.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id:"  + id));
    }
}
