package org.springbootapi.api.config;

import org.springbootapi.api.entities.Order;
import org.springbootapi.api.entities.User;
import org.springbootapi.api.entities.enums.OrderStatus;
import org.springbootapi.api.repositories.OrderRepository;
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

    public TestConfig(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
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
    }
}
