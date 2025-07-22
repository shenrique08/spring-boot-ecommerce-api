package org.api.springbootapiumlcase.services;

import org.api.springbootapiumlcase.domain.Customer;
import org.api.springbootapiumlcase.repositories.CustomerRepository;
import org.api.springbootapiumlcase.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {


    private final CustomerRepository repo;

    @Autowired
    public UserDetailsServiceImp(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = repo.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(customer.getId(), customer.getEmail(), customer.getPassword());
    }
}
