package com.mesarikaya.restapiwithspringwebflux.Services;

import com.mesarikaya.restapiwithspringwebflux.api.model.CustomerDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Customer;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomerService {

    Mono<List<CustomerDTO>> getByLastName(String lastName);
    Mono<List<CustomerDTO>> getCustomers();
    Mono<Long> count();
    Mono<Customer> saveCustomer(CustomerDTO customerDTO);
    Mono<CustomerDTO> getByID(String id);
    Mono<CustomerDTO> createNewCustomer(CustomerDTO customerDTO);
    Mono<CustomerDTO> saveorUpdateCustomer(String id, CustomerDTO customerDTO);
    Mono<CustomerDTO> patch(String id, CustomerDTO customerDTO);
    Mono<Void> deleteCustomerByID(String  id);
}
