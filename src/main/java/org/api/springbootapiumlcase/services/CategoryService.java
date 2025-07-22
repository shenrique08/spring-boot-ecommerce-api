package org.api.springbootapiumlcase.services;


import jakarta.persistence.EntityNotFoundException;
import org.api.springbootapiumlcase.domain.Category;
import org.api.springbootapiumlcase.dto.CategoryDTO;
import org.api.springbootapiumlcase.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;

    }

    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    public List<CategoryDTO> findAll() {
        List<Category> categories = repository.findAll();
        return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public Category insert(Category obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public void update(Category obj) {
        Category newObj = findById(obj.getId());
        updateData(newObj, obj);
        repository.save(obj);
    }

    public void deleteById(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Cannot delete category that has products.");
        }
    }

    public Category fromDTO(CategoryDTO objDto) {
        return Category.builder()
                .id(objDto.getId())
                .name(objDto.getName())
                .build();
    }

    private void updateData(Category newObj, Category obj) {
        newObj.setName(obj.getName());
    }
}
