package com.som.cqrs.repository;

import com.som.cqrs.entity.ProductCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductCommand,Long> {
}
