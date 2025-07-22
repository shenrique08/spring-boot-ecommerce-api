package org.api.springbootapiumlcase.services;

import jakarta.persistence.EntityNotFoundException;
import org.api.springbootapiumlcase.domain.Product;
import org.api.springbootapiumlcase.dto.ProductDTO;
import org.api.springbootapiumlcase.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    public List<ProductDTO> findAll() {
        List<Product> products = repository.findAll();
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public Product insert(Product obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public void update(Product obj) {
        Product newObj = findById(obj.getId());
        updateData(newObj, obj);
        repository.save(newObj);
    }

    public void deleteById(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Cannot delete a product that is part of an order.");
        }
    }

    public Product fromDTO(ProductDTO objDto) {
        return Product.builder()
                .id(objDto.getId())
                .name(objDto.getName())
                .price(objDto.getPrice())
                .build();
    }

    private void updateData(Product newObj, Product obj) {
        newObj.setPrice(obj.getPrice());
        newObj.setName(obj.getName());
    }
}
