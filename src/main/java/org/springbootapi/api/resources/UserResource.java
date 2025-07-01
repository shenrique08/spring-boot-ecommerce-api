package org.springbootapi.api.resources;

import org.springbootapi.api.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll() {
        User userExample = User.builder()
                .id(1L)
                .name("Maria")
                .email("maria@example.com")
                .phone("999999")
                .password("43554")
                .build();
        return ResponseEntity.ok().body(userExample);
    }
}
