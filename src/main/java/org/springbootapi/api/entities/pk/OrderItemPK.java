package org.springbootapi.api.entities.pk;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springbootapi.api.entities.Order;
import org.springbootapi.api.entities.Product;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
