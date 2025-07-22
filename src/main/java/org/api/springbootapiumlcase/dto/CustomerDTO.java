package org.api.springbootapiumlcase.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.springbootapiumlcase.domain.Customer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Getter
@Setter
public class CustomerDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Name is required.")
    @Length(min = 5, max = 120, message = "the length must be between 5 and 100 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email.")
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
    }
}
