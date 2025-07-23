package org.api.springbootapiumlcase.resources;

import org.api.springbootapiumlcase.services.CategoryService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryResourceTest {

    @Autowired
    public CategoryResourceTest(MockMvc mockMvc, CategoryService categoryService) {
    }

    @TestConfiguration
    static class CategoryResourceTestConfig {
        @Bean
        public CategoryService categoryService() {
            return Mockito.mock(CategoryService.class);
        }
    }
}