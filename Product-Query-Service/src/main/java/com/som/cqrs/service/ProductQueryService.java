package com.som.cqrs.service;

import com.som.cqrs.entity.ProductQuery;
import com.som.cqrs.model.Product;
import com.som.cqrs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {
    @Autowired private ProductRepository productRepository;

    public ProductQuery getProductById(Long id){
        return productRepository.findById(id).get();
    }

    public List<ProductQuery> getAllProducts(){
        return productRepository.findAll();
    }
}
