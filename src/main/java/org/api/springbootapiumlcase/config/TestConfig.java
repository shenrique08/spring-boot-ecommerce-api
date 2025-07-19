package org.api.springbootapiumlcase.config;

import org.api.springbootapiumlcase.domain.Category;
import org.api.springbootapiumlcase.domain.Product;
import org.api.springbootapiumlcase.repositories.CategoryRepository;
import org.api.springbootapiumlcase.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public TestConfig(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {

        Category category1 = Category.builder().name("Electronics").build();
        Category category2 = Category.builder().name("Books").build();
        Category category3 = Category.builder().name("Computers").build();

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));

        Product product1 = Product.builder().name("Laptop Lenovo").price(3000.0).build();
        Product product2 = Product.builder().name("Printer").price(1200.0).build();
        Product product3 = Product.builder().name("The Hobbit").price(45.0).build();
        Product product4 = Product.builder().name("9600 XT 16GB").price(3000.0).build();

        product1.getCategoryList().add(category3);
        product2.getCategoryList().add(category1);
        product3.getCategoryList().add(category2);
        product4.getCategoryList().add(category1);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));
    }
}