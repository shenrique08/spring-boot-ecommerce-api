package org.api.springbootapiumlcase.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.springbootapiumlcase.domain.Product;

import java.io.Serializable;


@Getter
@Setter
public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private Double price;

    public ProductDTO() {
    }

    public ProductDTO(Product obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.price = obj.getPrice();
    }
}
