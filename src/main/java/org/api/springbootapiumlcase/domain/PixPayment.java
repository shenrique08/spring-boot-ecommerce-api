package org.api.springbootapiumlcase.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expireDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datePayment;

    public PixPayment() {}

    @Builder
    public PixPayment(Long id, StatePayment statePayment, Order order, LocalDate expireDate, LocalDate datePayment) {
        super(id, statePayment, order);
        this.expireDate = expireDate;
        this.datePayment = datePayment;
    }
}
