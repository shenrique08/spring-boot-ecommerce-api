package org.springbootapi.api.resources;


import org.springbootapi.api.entities.Category;
import org.springbootapi.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    private final CategoryService service;

    @Autowired
    public CategoryResource(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categoryList = service.findAll();
        return ResponseEntity.ok().body(categoryList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElse(ResponseEntity.notFound().build());
    }
}
