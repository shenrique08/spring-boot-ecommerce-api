package org.api.springbootapiumlcase.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class ShoppingCart implements Serializable {

    @Id
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "customer_id")
    @MapsId
    private Customer customer;

    @OneToMany(mappedBy = "id.shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> items = new HashSet<>();
}
