package org.api.springbootapiumlcase.domain.enums;


import lombok.Getter;

@Getter
public enum StatePayment {

    PENDING(1),
    PAYD(2),
    CANCELED(3);

    private final int code;

    StatePayment(int i) {
        this.code = i;
    }

    public static StatePayment toEnum(Integer cod) {
        if (cod == null)
            return null;

        for (StatePayment statePayment : StatePayment.values()) {
            if (statePayment.getCode() == cod)
                return statePayment;
        }

        throw new IllegalArgumentException("Invalid code: " + cod);
    }
}
