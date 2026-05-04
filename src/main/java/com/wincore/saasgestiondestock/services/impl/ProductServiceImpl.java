package com.wincore.saasgestiondestock.services.impl;

import com.wincore.saasgestiondestock.dtos.product.ProductRequest;
import com.wincore.saasgestiondestock.dtos.product.ProductResponse;
import com.wincore.saasgestiondestock.entities.Category;
import com.wincore.saasgestiondestock.entities.Product;
import com.wincore.saasgestiondestock.mappers.ProductMapper;
import com.wincore.saasgestiondestock.repositories.CategoryRepository;
import com.wincore.saasgestiondestock.repositories.ProductRepository;
import com.wincore.saasgestiondestock.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public void create(ProductRequest request) {
        checkIfProductAlreadyExistByRef(request.getReference());
        checkIfCategoryExist(request.getCategoryId());

        final Product product = productMapper.toEntity(request);
        productRepository.save(product);
    }

    @Override
    public void update(Long id, ProductRequest request) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product Not found");
        }

        checkIfProductAlreadyExistByRef(request.getReference());
        checkIfCategoryExist(request.getCategoryId());

        Product savedProduct = productMapper.toEntity(request);
        productRepository.save(savedProduct);
    }

    @Override
    public List<ProductResponse> findAll(int page, int size) {
        List<Product> products = productRepository.findAll();

        return products.stream().map(productMapper::toResponse).toList();
    }

    @Override
    public ProductResponse findById(Long id) {

        return productRepository.findById(id).map(productMapper::toResponse).orElseThrow(
                ()-> new EntityNotFoundException("product not found")
        );
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("product not found"));
    }

    private void checkIfProductAlreadyExistByRef(final String ref) {

        Optional<Product> product = productRepository.findByReference(ref);
        if(product.isPresent()) {

            throw new RuntimeException("Product already exist");
        }
    }

    private void checkIfCategoryExist (final Long id) {

        final Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            throw new RuntimeException("Category already exist");
        }
    }
}
