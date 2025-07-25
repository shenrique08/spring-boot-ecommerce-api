package org.api.springbootapiumlcase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.api.springbootapiumlcase.domain.enums.CustomerType;

import java.io.Serializable;
import java.util.*;


@Getter
@Setter
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String identificationNumber;
    private String password;
    private Integer customerType;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "customer")
    private final List<Address> addresses = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "PHONE")
    private final Set<String> phones = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public Customer() {}

    @Builder
    public Customer(Long id, String name, String email, String password, String identificationNumber, CustomerType customerType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.identificationNumber = identificationNumber;
        this.customerType = (customerType == null) ? null : customerType.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(identificationNumber, customer.identificationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificationNumber);
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType.getCod();
    }

    public CustomerType getCustomerType() {
        return CustomerType.toEnum(customerType);
    }
}
