package org.api.springbootapiumlcase.services;

import org.api.springbootapiumlcase.domain.Category;
import org.api.springbootapiumlcase.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenFindById_thenReturnCategory() {
        Category category = new Category(1L, "Electronics");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));


        Category found = categoryService.findById(1L);

        assertEquals(category.getName(), found.getName());
    }
}