package com.wincore.saasgestiondestock.services.impl;

import com.wincore.saasgestiondestock.dtos.category.CategoryRequest;
import com.wincore.saasgestiondestock.dtos.category.CategoryResponse;
import com.wincore.saasgestiondestock.entities.Category;
import com.wincore.saasgestiondestock.mappers.CategoryMapper;
import com.wincore.saasgestiondestock.repositories.CategoryRepository;
import com.wincore.saasgestiondestock.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void create(CategoryRequest request) {
        final Optional<Category> category = categoryRepository.findByNameIgnoreCase(request.getName());
        if(category.isPresent()) {
            throw new RuntimeException(" cartegory already exist");
        }

        Category savedCategory = categoryMapper.toEntity(request);
        categoryRepository.save(savedCategory);

    }

    @Override
    public void update(Long id, CategoryRequest request) {

        final Optional<Category> existing = categoryRepository.findById(id);
        if(existing.isEmpty()) {
            throw new EntityNotFoundException("not found");
        }

        final Optional<Category> category = categoryRepository.findByNameIgnoreCase(request.getName());
        if(category.isPresent()) {
            throw new RuntimeException(" cartegory already exist");
        }

        final Category catgoryToUpdate = categoryMapper.toEntity(request);
        categoryRepository.save(catgoryToUpdate);
    }

    @Override
    public List<CategoryResponse> findAll(int page, int size) {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse findById(Long id) {
        return categoryRepository.findById(id).map(this.categoryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public void delete(Long id) {
      final Category category =   categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
      categoryRepository.delete(category);
    }
}
