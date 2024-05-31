package com.som.cqrs.service;

import com.som.cqrs.entity.ProductCommand;
import com.som.cqrs.model.Product;
import com.som.cqrs.model.ProductEvent;
import com.som.cqrs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductCommandService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public ProductCommand createProduct(Product product){
        ProductCommand productCommand = new ProductCommand();
        productCommand.setName(product.getProductName());
        productCommand.setDescription(product.getDescription());
        productCommand.setPrice(product.getPrice());
        ProductCommand savedProduct=  productRepository.save(productCommand);
        kafkaTemplate.send("Product-topic",new ProductEvent("create",new Product(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(), savedProduct.getPrice())));
        return savedProduct;
    }
    public ProductCommand updateProduct(Long id, Product product){
        Optional<ProductCommand> optionalProductQuery = productRepository.findById(id);
        if(optionalProductQuery.isPresent()){
            ProductCommand productCommand = optionalProductQuery.get();
            productCommand.setName(product.getProductName());
            productCommand.setDescription(product.getDescription());
            productCommand.setPrice(product.getPrice());
            productRepository.save(productCommand);
            kafkaTemplate.send("Product-topic",new ProductEvent("update",new Product(productCommand.getId(), productCommand.getName(), productCommand.getDescription(), productCommand.getPrice())));
            return productCommand;
        }else {
            throw new RuntimeException("Product "+id+" not found");
        }
    }
//    public void deleteProduct(Long id){
//        productRepository.deleteById(id);
//        kafkaTemplate.send("Product-topic",id);
//    }
}
