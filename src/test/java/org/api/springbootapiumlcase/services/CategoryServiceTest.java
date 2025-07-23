package org.api.springbootapiumlcase.services;

import org.api.springbootapiumlcase.domain.Category;
import org.api.springbootapiumlcase.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
    public void whenDeleteById_thenSuccess() {
        Category category = new Category(1L, "Electronics");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteById(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteByIdWithAssociatedProducts_thenThrowException() {
        Category category = new Category(1L, "Electronics");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        doThrow(new DataIntegrityViolationException("")).when(categoryRepository).deleteById(1L);

        assertThrows(DataIntegrityViolationException.class, () -> {
            categoryService.deleteById(1L);
        });

    }
}