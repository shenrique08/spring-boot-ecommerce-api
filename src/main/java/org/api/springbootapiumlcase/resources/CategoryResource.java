package org.api.springbootapiumlcase.resources;

import jakarta.persistence.Entity;
import org.api.springbootapiumlcase.domain.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @GetMapping
    public List<Category> list() {
        Category category = Category.builder()
                .name("Tech")
                .build();
        Category category1 = Category.builder()
                .name("Office")
                .build();

        return List.of(category1, category);
    }
}
