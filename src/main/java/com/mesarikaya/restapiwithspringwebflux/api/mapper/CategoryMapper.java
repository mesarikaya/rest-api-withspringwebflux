package com.mesarikaya.restapiwithspringwebflux.api.mapper;

import com.mesarikaya.restapiwithspringwebflux.api.model.CategoryDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // @Mapping(source="getId", target="id")
    CategoryDTO categoryToCategoryDTO(Category category);
    Category categoryDTOtoCategory(CategoryDTO categoryDTO);
}
