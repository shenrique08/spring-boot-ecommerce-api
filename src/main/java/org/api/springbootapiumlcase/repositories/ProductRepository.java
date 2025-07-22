package org.api.springbootapiumlcase.repositories;

import org.api.springbootapiumlcase.domain.Category;
import org.api.springbootapiumlcase.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional(readOnly = true)
    Page<Product> findDistinctByNameContainingAndCategoryListIn(String name, List<Category> categories, Pageable pageRequest);
}
