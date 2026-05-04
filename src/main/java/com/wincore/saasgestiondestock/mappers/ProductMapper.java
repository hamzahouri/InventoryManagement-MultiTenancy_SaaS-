package com.wincore.saasgestiondestock.mappers;

import com.wincore.saasgestiondestock.dtos.product.ProductRequest;
import com.wincore.saasgestiondestock.dtos.product.ProductResponse;
import com.wincore.saasgestiondestock.entities.Category;
import com.wincore.saasgestiondestock.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public Product toEntity (final ProductRequest productRequest) {

        return Product.builder()
                .name(productRequest.getName())
                .category(Category.builder().id(productRequest.getCategoryId()).build())
                .alertThreshold(productRequest.getAlertThreshold())
                .description(productRequest.getDescription())
                .reference(productRequest.getReference())
                .price(productRequest.getPrice())
                .build();
    }

    public ProductResponse toResponse (final Product entity) {

        return ProductResponse.builder()
                .name(entity.getName())
                .alertThreshold(entity.getAlertThreshold())
                .description(entity.getDescription())
                .reference(entity.getReference())
                .price(entity.getPrice())
                .categoryName(entity.getCategory().getName())
                .build();
    }

}
