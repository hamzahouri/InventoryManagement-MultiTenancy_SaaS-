package com.wincore.saasgestiondestock.mappers;

import com.wincore.saasgestiondestock.dtos.category.CategoryRequest;
import com.wincore.saasgestiondestock.dtos.category.CategoryResponse;
import com.wincore.saasgestiondestock.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {


    public Category toEntity (final CategoryRequest categoryRequest) {

        return Category.builder()
                .description(categoryRequest.getDescription())
                .name(categoryRequest.getName()).build();
    }

    public CategoryResponse toResponse (final Category entity) {
        return CategoryResponse.builder()
                .description(entity.getDescription())
                .name(entity.getName())
                .nbProducts(entity.getProducts().size())
                .build()
                ;
    }
}

