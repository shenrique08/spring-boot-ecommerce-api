package org.api.springbootapiumlcase.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime instant;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "deliver_address_id")
    private Address deliverAddress;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order() {}

    @Builder
    public Order(Long id, LocalDateTime instant, Payment payment, Customer customer, Address deliverAddress) {
        this.id = id;
        this.instant = instant;
        this.payment = payment;
        this.customer = customer;
        this.deliverAddress = deliverAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
