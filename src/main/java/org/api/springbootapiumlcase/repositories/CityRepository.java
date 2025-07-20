package org.api.springbootapiumlcase.repositories;

import org.api.springbootapiumlcase.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
