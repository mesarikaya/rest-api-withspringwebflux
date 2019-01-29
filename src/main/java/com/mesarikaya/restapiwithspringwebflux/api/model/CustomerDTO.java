package com.mesarikaya.restapiwithspringwebflux.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private String id;

    @ApiModelProperty(value = "This is the first name of the customer", required = true)
    private String firstName;

    @ApiModelProperty(value = "This is the last name of the customer", required = true)
    private String lastName;
}
