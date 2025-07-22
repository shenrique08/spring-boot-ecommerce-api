package org.api.springbootapiumlcase.resources;

import org.api.springbootapiumlcase.domain.Category;
import org.api.springbootapiumlcase.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito; // Import Mockito
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration; // Import TestConfiguration
import org.springframework.context.annotation.Bean; // Import Bean
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryResourceTest {

    private final MockMvc mockMvc;
    private final CategoryService categoryService;

    @Autowired
    public CategoryResourceTest(MockMvc mockMvc, CategoryService categoryService) {
        this.mockMvc = mockMvc;
        this.categoryService = categoryService;
    }

    @TestConfiguration
    static class CategoryResourceTestConfig {
        @Bean
        public CategoryService categoryService() {
            return Mockito.mock(CategoryService.class);
        }
    }

    @Test
    public void whenFindById_thenReturnCategory() throws Exception {
        Category category = new Category(1L, "Electronics");
        when(categoryService.findById(1L)).thenReturn(category);

        mockMvc.perform(get("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electronics"));
    }
}