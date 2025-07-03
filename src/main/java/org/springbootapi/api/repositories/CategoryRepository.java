package org.springbootapi.api.repositories;

import org.springbootapi.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
