package com.som.cqrs.service;

import com.som.cqrs.entity.ProductQuery;
import com.som.cqrs.model.Product;
import com.som.cqrs.model.ProductEvent;
import com.som.cqrs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductEventService {
    @Autowired
    ProductRepository productRepository;

    @KafkaListener(topics = "Product-topic", groupId = "product-group-1")
    public void eventListener(ProductEvent event){
        Product product = event.getProduct();
        ProductQuery productQuery = new ProductQuery();
        switch(event.getEvent()){
            case "create":
                productQuery.setName(product.getProductName());
                productQuery.setDescription(product.getDescription());
                productQuery.setPrice(product.getPrice());
                productRepository.save(productQuery);
                break;
            case "update":
                Optional<ProductQuery> optionalProduct = productRepository.findById(product.getId());
                productQuery = optionalProduct.get();
                productQuery.setName(product.getProductName());
                productQuery.setDescription(product.getDescription());
                productQuery.setPrice(product.getPrice());
                productRepository.save(productQuery);
                break;
        }
    }
}
