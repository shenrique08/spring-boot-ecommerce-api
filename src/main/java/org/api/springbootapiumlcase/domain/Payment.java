package org.api.springbootapiumlcase.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.api.springbootapiumlcase.domain.enums.StatePayment;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    @Id
    private Long id;
    private Integer statePayment;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    public Payment() {}

    public Payment(Long id, StatePayment statePayment, Order order) {
        this.id = id;
        this.statePayment = statePayment.getCode();
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public StatePayment getStatePayment() {
        return StatePayment.toEnum(statePayment);
    }

    public void setStatePayment(StatePayment statePayment) {
        this.statePayment = statePayment.getCode();
    }
}
