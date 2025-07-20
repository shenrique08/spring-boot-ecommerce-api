package org.api.springbootapiumlcase.repositories;

import org.api.springbootapiumlcase.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
