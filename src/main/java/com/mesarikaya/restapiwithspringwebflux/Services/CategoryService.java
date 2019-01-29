package com.mesarikaya.restapiwithspringwebflux.Services;

import com.mesarikaya.restapiwithspringwebflux.api.model.CategoryDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Category;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CategoryService {

    Mono<Category> saveCategory(CategoryDTO category);
    Mono<Long> count();
    Mono<List<CategoryDTO>> getAllCategories();
    Mono<List<CategoryDTO>> getCategoryByName(String name);
    Mono<CategoryDTO> getByID(String id);
    Mono<Void> deleteById(String id);
    Mono<CategoryDTO> patch(String id, CategoryDTO categoryDTO);
}
