package com.som.cqrs.controller;

import com.som.cqrs.entity.ProductQuery;
import com.som.cqrs.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    ProductQueryService productQueryService;

    @GetMapping
    public ResponseEntity<List<ProductQuery>> retrieveAllProducts(){
        return new ResponseEntity<>(productQueryService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductQuery> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productQueryService.getProductById(id), HttpStatus.OK);
    }

}
