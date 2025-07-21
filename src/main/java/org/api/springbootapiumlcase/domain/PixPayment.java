package org.api.springbootapiumlcase.domain;


import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.api.springbootapiumlcase.domain.enums.StatePayment;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class PixPayment extends Payment {

    private LocalDate expireDate;
    private LocalDate datePayment;

    public PixPayment() {}

    @Builder
    public PixPayment(Long id, StatePayment statePayment, Order order, LocalDate expireDate, LocalDate datePayment) {
        super(id, statePayment, order);
        this.expireDate = expireDate;
        this.datePayment = datePayment;
    }
}
