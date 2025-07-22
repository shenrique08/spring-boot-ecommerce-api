package org.api.springbootapiumlcase.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.springbootapiumlcase.domain.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Getter
@Setter
public class CategoryDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Name is required.")
    @Length(min = 3, max = 80, message = "The length must be between 3 and 80 characters")
    private String name;

    public CategoryDTO() {}

    public CategoryDTO(Category obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }
}
