package org.api.springbootapiumlcase.resources;


import org.api.springbootapiumlcase.domain.Category;
import org.api.springbootapiumlcase.dto.CategoryDTO;
import org.api.springbootapiumlcase.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService service) {
        this.categoryService = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categoryDTOList = categoryService.findAll();
        return ResponseEntity.ok().body(categoryDTOList);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody CategoryDTO objDTO) {
        Category obj = categoryService.fromDTO(objDTO);
        obj = categoryService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody CategoryDTO objDTO, @PathVariable Long id) {
        Category obj = categoryService.fromDTO(objDTO);
        obj.setId(id);
        categoryService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
