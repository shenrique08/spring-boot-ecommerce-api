package org.api.springbootapiumlcase.repositories;


import org.api.springbootapiumlcase.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Transactional(readOnly = true)
    Customer findByEmail(String email);
}
