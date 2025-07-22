package org.api.springbootapiumlcase.resources;

import jakarta.validation.Valid;
import org.api.springbootapiumlcase.domain.Customer;
import org.api.springbootapiumlcase.dto.CustomerDTO;
import org.api.springbootapiumlcase.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    private final CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok().body(new CustomerDTO(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<CustomerDTO> list = customerService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerDTO objDTO) {
        Customer obj = customerService.fromDTO(objDTO);
        obj = customerService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CustomerDTO objDTO, @PathVariable Long id) {
        Customer obj = customerService.fromDTO(objDTO);
        obj.setId(id);
        customerService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}