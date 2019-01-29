package com.mesarikaya.restapiwithspringwebflux.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDTO {

    private String id;

    @ApiModelProperty(value = "This is the name of the category")
    private String name;
}
