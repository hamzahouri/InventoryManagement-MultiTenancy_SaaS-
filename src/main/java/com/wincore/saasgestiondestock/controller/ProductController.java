package com.wincore.saasgestiondestock.controller;

import com.wincore.saasgestiondestock.dtos.product.ProductRequest;
import com.wincore.saasgestiondestock.dtos.product.ProductResponse;
import com.wincore.saasgestiondestock.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;


    @PostMapping
    public ResponseEntity<Void> createProduct(
            @Valid
            @RequestBody ProductRequest request
    ) {
        service.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/id")
    public ResponseEntity<Void> updateProduct(
            @Valid
            @RequestBody ProductRequest request,
            @PathVariable("id") Long id

    ) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id")
    public ResponseEntity<ProductResponse> getProduct(

            @PathVariable("id") Long id

    ) {
        service.findById(id);
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "0") int size
    ) {

        return ResponseEntity.ok(this.service.findAll(page,size));
    }


    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("id") Long id
    ) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
