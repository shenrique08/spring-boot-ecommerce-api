package org.springbootapi.api.config;

import org.springbootapi.api.entities.User;
import org.springbootapi.api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {


    private final UserRepository userRepository;

    public TestConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}