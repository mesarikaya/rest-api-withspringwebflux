package com.mesarikaya.restapiwithspringwebflux.api.mapper;

import com.mesarikaya.restapiwithspringwebflux.api.model.CustomerDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    // @Mapping(source="getId", target="id")
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOtoCustomer(CustomerDTO customerDTO);
}
