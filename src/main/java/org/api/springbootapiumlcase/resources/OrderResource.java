package org.api.springbootapiumlcase.resources;


import org.api.springbootapiumlcase.domain.Order;
import org.api.springbootapiumlcase.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    private final OrderService service;

    public OrderResource(OrderService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order order = service.findById(id);
        return ResponseEntity.ok().body(order);
    }
}
