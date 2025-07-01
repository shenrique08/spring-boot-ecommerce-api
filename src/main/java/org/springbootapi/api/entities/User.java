package org.springbootapi.api.entities;


import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Objects;

@Log4j2
@Builder
@Getter
@Setter
@ToString
public class User implements Serializable {
    Long id;
    String name;
    String email;
    String phone;
    String password;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
