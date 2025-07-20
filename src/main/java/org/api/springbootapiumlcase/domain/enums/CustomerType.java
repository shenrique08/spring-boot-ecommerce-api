package org.api.springbootapiumlcase.domain.enums;


import lombok.Getter;

@Getter
public enum CustomerType {

    INDIVIDUAL(1),
    LEGAL_ENTITY(2);

    final int cod;

    CustomerType(int i) {
        this.cod = i;
    }

    public static CustomerType toEnum(Integer cod) {
        if (cod == null)
            return null;

        for (CustomerType type : CustomerType.values()) {
            if (type.getCod() == cod)
                return type;
        }

        throw new IllegalArgumentException("Invalid code: " + cod);
    }
}
