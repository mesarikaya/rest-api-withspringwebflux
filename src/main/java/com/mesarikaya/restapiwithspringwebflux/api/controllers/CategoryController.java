package com.mesarikaya.restapiwithspringwebflux.api.controllers;

import com.mesarikaya.restapiwithspringwebflux.Services.CategoryService;
import com.mesarikaya.restapiwithspringwebflux.api.model.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public Mono<List<CategoryDTO>> getAllCategories(){

        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<List<CategoryDTO>> getCategoryByName(@PathVariable String name){

        return categoryService.getCategoryByName(name);
    }

    @GetMapping("/categories/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CategoryDTO> getCategoryById(@PathVariable String id){
        return categoryService.getByID(id);
    }
}
