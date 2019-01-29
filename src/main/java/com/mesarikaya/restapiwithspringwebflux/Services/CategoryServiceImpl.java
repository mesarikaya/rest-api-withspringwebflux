package com.mesarikaya.restapiwithspringwebflux.Services;


import com.mesarikaya.restapiwithspringwebflux.Repositories.CategoryRepository;
import com.mesarikaya.restapiwithspringwebflux.api.mapper.CategoryMapper;
import com.mesarikaya.restapiwithspringwebflux.api.model.CategoryDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Category;
import org.bson.codecs.ObjectIdGenerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Mono<Category> saveCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOtoCategory(categoryDTO);
        category.setId(new ObjectIdGenerator().generate().toString());
        return categoryRepository.save(category);
    }

    @Override
    public Mono<Long> count() {
        return categoryRepository.count();
    }

    @Override
    public Mono<List<CategoryDTO>> getAllCategories() {
        Flux<Category> categories = categoryRepository.findAll();
        Mono<List<CategoryDTO>> categoryDTOcategoryList = categories.map(categoryMapper::categoryToCategoryDTO).collectList();

        return categoryDTOcategoryList;
    }

    @Override
    public Mono<List<CategoryDTO>> getCategoryByName(String name) {
        Flux<Category> categories = categoryRepository.findByName(name);
        Mono<List<CategoryDTO>> categoryDTOcategoryList = categories.map(categoryMapper::categoryToCategoryDTO).collectList();

        return categoryDTOcategoryList;
    }

    @Override
    public Mono<CategoryDTO> getByID(String id) {
        Mono<Category> category = categoryRepository.findById(id);

        if(category != null){
            Mono<CategoryDTO> categoryDTO = category.map(categoryMapper::categoryToCategoryDTO);
            return categoryDTO;
        }else{
            throw new RuntimeException("No such id exists!");
        }
    }


    @Override
    public Mono<Void> deleteById(String id) {
        return categoryRepository.deleteById(id);
    }

    @Override
    public Mono<CategoryDTO> patch(String id, CategoryDTO categoryDTO) {

        Category foundCategory = categoryRepository.findById(id).block();

        if (foundCategory.getName() != categoryDTO.getName()) {
            foundCategory.setName(categoryDTO.getName());
            categoryRepository.save(foundCategory);
        }

        CategoryDTO returnToDTO = categoryMapper.categoryToCategoryDTO(foundCategory);

        return Mono.just(returnToDTO);
    }

}
