package org.api.springbootapiumlcase.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.springbootapiumlcase.domain.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;


@Getter
@Setter
public class ProductDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Name is required.")
    private String name;

    @NotNull(message = "Price is required.")
    @Positive(message = "Price must be a positive value.")
    private Double price;

    public ProductDTO() {
    }

    public ProductDTO(Product obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.price = obj.getPrice();
    }
}
