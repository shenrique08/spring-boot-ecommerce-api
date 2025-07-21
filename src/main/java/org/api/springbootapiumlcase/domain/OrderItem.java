package org.api.springbootapiumlcase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@Entity
public class OrderItem implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private final OrderItemPK id = new OrderItemPK();

    private Double discount;
    private Integer quantity;
    private Double price;

    public OrderItem() {}

    @Builder
    public OrderItem(Order order, Product product, Double discount, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
