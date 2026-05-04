package com.wincore.saasgestiondestock.controller;

import com.wincore.saasgestiondestock.dtos.category.CategoryRequest;
import com.wincore.saasgestiondestock.dtos.category.CategoryResponse;
import com.wincore.saasgestiondestock.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<Void> createCategory(
            @Valid
            @RequestBody CategoryRequest request
    ) {
        service.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/id")
    public ResponseEntity<Void> updateCategory(
            @Valid
            @RequestBody CategoryRequest request,
            @PathVariable("id") Long id

    ) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id")
    public ResponseEntity<CategoryResponse> getCategory(

            @PathVariable("id") Long id

    ) {
        service.findById(id);
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getCategories(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "0") int size
    ) {

        return ResponseEntity.ok(this.service.findAll(page,size));
    }


    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable("id") Long id
    ) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
