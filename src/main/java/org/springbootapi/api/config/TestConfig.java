package org.springbootapi.api.config;

import org.springbootapi.api.entities.Category;
import org.springbootapi.api.entities.Order;
import org.springbootapi.api.entities.Product;
import org.springbootapi.api.entities.User;
import org.springbootapi.api.entities.enums.OrderStatus;
import org.springbootapi.api.repositories.CategoryRepository;
import org.springbootapi.api.repositories.OrderRepository;
import org.springbootapi.api.repositories.ProductRepository;
import org.springbootapi.api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public TestConfig(ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User u1 = User.builder()
                .name("Maria Brown")
                .email("maria@gmail.com")
                .phone("988888888")
                .password("123456")
                .build();

        User u2 = User.builder()
                .name("Alex Green")
                .email("alex@gmail.com")
                .phone("977777777")
                .password("123456")
                .build();

        User u3 = User.builder()
                .name("Bob Grey")
                .email("bob@gmail.com")
                .phone("966666666")
                .password("123456")
                .build();

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Order o1 = Order.builder()
                .moment(Instant.parse("2025-06-20T19:53:07Z"))
                .client(u1)
                .orderStatus(OrderStatus.PAID)
                .build();
        Order o2 = Order.builder()
                .moment(Instant.parse("2025-07-21T03:42:10Z"))
                .client(u3)
                .orderStatus(OrderStatus.CANCELED)
                .build();
        Order o3 = Order.builder()
                .moment(Instant.parse("2025-07-22T15:21:22Z"))
                .orderStatus(OrderStatus.SHIPPED)
                .client(u1)
                .build();

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        Category category = Category.builder()
                .name("Books")
                .build();
        Category category1 = Category.builder()
                .name("Computers")
                .build();
        Category category2 = Category.builder()
                .name("Electronics")
                .build();

        categoryRepository.saveAll(Arrays.asList(category1, category, category2));

        Product p1 = Product.builder()
                .name("The Lord of the Rings")
                .description("Lorem ipsum dolor sit amet, consectetur.")
                .price(90.5)
                .imgUrl("")
                .build();

        Product p2 = Product.builder()
                .name("Smart TV LG 32'")
                .description("Nulla eu imperdiet purus. Maecenas ante.")
                .price(2190.0)
                .imgUrl("")
                .build();

        Product p3 = Product.builder()
                .name("Macbook Pro 14")
                .description("Nam eleifend maximus tortor, at mollis.")
                .price(13_000.0)
                .imgUrl("")
                .build();

        Product p4 = Product.builder()
                .name("PS5")
                .description("Donec aliquet odio ac rhoncus cursus.")
                .price(3000.0)
                .imgUrl("")
                .build();

        Product p5 = Product.builder()
                .name("Python for Dummies")
                .description("Cras fringilla convallis sem vel faucibus.")
                .price(100.99)
                .imgUrl("")
                .build();

        p1.getCategories().add(category);
        p2.getCategories().add(category2);
        p3.getCategories().add(category1);
        p4.getCategories().add(category2);
        p5.getCategories().add(category);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
    }
}