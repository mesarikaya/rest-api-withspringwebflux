package com.mesarikaya.restapiwithspringwebflux.api.controllers;


import com.mesarikaya.restapiwithspringwebflux.Services.CustomerService;
import com.mesarikaya.restapiwithspringwebflux.api.mapper.CustomerMapper;
import com.mesarikaya.restapiwithspringwebflux.api.model.CustomerDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Api("This is my Customer Controller")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @ApiOperation(value = "This will give the list of all customers.", notes = "These are some notes about the API.")
    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public Mono<List<CustomerDTO>> getCustomers(){

        return customerService.getCustomers();
    }

    @ApiOperation(value = "This will give the list of all customers with the provided lastname.", notes = "These are some notes about the API.")
    @GetMapping("/customers/get/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<List<CustomerDTO>> getCustomersByLastName(@PathVariable String lastName){

        return customerService.getByLastName(lastName);
    }

    @ApiOperation(value = "This enables search of customers based on id.", notes = "These are some notes about the API.")
    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CustomerDTO> getCustomerById(@PathVariable String id){

        return customerService.getByID(id);
    }

    @ApiOperation(value = "This will allow adding new customers to the list.", notes = "These are some notes about the API.")
    @PostMapping("/customers/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> addCustomer(@RequestBody CustomerDTO theCustomerDTO){


        Mono<Customer> savedCustomer = customerService.saveCustomer(theCustomerDTO);
        return savedCustomer.map(customerMapper::customerToCustomerDTO).then();
    }

    @ApiOperation(value = "This will enable updating the customer with all properties.", notes = "These are some notes about the API.")
    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO theCustomerDTO){

        return customerService.saveorUpdateCustomer(id, theCustomerDTO);
    }

    @ApiOperation(value = "This will enable to modify the customer partially or fully.", notes = "These are some notes about the API.")
    @PatchMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CustomerDTO> patchCustomer(@PathVariable String id, @RequestBody CustomerDTO theCustomerDTO){

        return customerService.patch(id, theCustomerDTO);
    }

    @ApiOperation(value = "This will delete the customer.", notes = "These are some notes about the API.")
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> deleteCustomerById(@PathVariable String id){
        return customerService.deleteCustomerByID(id);
    }
}
