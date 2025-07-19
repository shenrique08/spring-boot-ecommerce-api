package org.api.springbootapiumlcase.config;

import org.api.springbootapiumlcase.domain.Category;
import org.api.springbootapiumlcase.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public TestConfig(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Category category1 = Category.builder().name("Electronics").build();
        Category category2 = Category.builder().name("Books").build();
        Category category3 = Category.builder().name("Computers").build();

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));

    }
}
