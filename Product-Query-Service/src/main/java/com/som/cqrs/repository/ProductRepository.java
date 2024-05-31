package com.som.cqrs.repository;

import com.som.cqrs.entity.ProductQuery;
import com.som.cqrs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductQuery,Long> {
}
