package org.api.springbootapiumlcase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class CartItem implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private CartItemPK id = new CartItemPK();

    private Integer quantity;

    public CartItem() {}

    public CartItem(ShoppingCart shoppingCart, Product product, Integer quantity) {
        this.id.setShoppingCart(shoppingCart);
        this.id.setProduct(product);
        this.quantity = quantity;
    }

    @JsonIgnore
    public ShoppingCart getShoppingCart() {
        return id.getShoppingCart();
    }

    public Product getProduct() {
        return id.getProduct();
    }
}