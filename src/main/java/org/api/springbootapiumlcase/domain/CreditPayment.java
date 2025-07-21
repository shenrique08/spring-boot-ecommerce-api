package org.api.springbootapiumlcase.domain;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.api.springbootapiumlcase.domain.enums.StatePayment;


@Getter
@Setter
@Entity
public class CreditPayment extends Payment {

    private Integer numberOfInstallments;

    public CreditPayment() {}

    @Builder
    public CreditPayment(Long id, StatePayment statePayment, Order order, Integer numberOfInstallments) {
        super(id, statePayment, order);
        this.numberOfInstallments = numberOfInstallments;
    }
}
