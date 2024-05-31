package com.som.cqrs.controller;

import com.som.cqrs.entity.ProductCommand;
import com.som.cqrs.model.Product;
import com.som.cqrs.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    @Autowired
    ProductCommandService productCommandService;

    @PostMapping
    public ResponseEntity<ProductCommand> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(productCommandService.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCommand> addProduct(@PathVariable Long id, @RequestBody Product product){
        return new ResponseEntity<>(productCommandService.updateProduct(id,product), HttpStatus.OK);
    }
}
