package org.api.springbootapiumlcase.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.springbootapiumlcase.domain.Category;

import java.io.Serializable;


@Getter
@Setter
public class CategoryDTO implements Serializable {

    private Long id;
    private String name;

    public CategoryDTO() {}

    public CategoryDTO(Category obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }
}
