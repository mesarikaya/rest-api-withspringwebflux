package com.mesarikaya.restapiwithspringwebflux.Services;

import com.mesarikaya.restapiwithspringwebflux.Repositories.CustomerRepository;
import com.mesarikaya.restapiwithspringwebflux.api.mapper.CustomerMapper;
import com.mesarikaya.restapiwithspringwebflux.api.model.CustomerDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Customer;
import org.bson.codecs.ObjectIdGenerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Mono<List<CustomerDTO>> getByLastName(String lastName) {
        Flux<Customer> customers = customerRepository.findByLastName(lastName);
        Mono<List<CustomerDTO>> customerDTOcustomerList = customers.map(customerMapper::customerToCustomerDTO).collectList();

        return customerDTOcustomerList;
    }

    @Override
    public Mono<List<CustomerDTO>> getCustomers() {
        Flux<Customer> customers = customerRepository.findAll();
        Mono<List<CustomerDTO>> customerDTOcustomerList = customers.map(customerMapper::customerToCustomerDTO).collectList();

        return customerDTOcustomerList;
    }

    @Override
    public Mono<Long> count() {
        return customerRepository.count();
    }

    @Override
    public Mono<Customer> saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer.setId(new ObjectIdGenerator().generate().toString());
        return customerRepository.save(customer);
    }

    @Override
    public Mono<CustomerDTO> getByID(String id) {
        Mono<Customer> customer = customerRepository.findById(id);

        if(customer != null){
            Mono<CustomerDTO> customerDTO = customer.map(customerMapper::customerToCustomerDTO);
            return customerDTO;
        }else{
            throw new RuntimeException("No such id exists!");
        }
    }

    @Override
    public Mono<CustomerDTO> createNewCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer.setId(new ObjectIdGenerator().generate().toString());
        Mono<Customer> savedCustomer = customerRepository.save(customer);

        Mono<CustomerDTO> returnToDTO = savedCustomer.map(customerMapper::customerToCustomerDTO);

        return returnToDTO;
    }

    @Override
    public Mono<CustomerDTO> saveorUpdateCustomer(String id, CustomerDTO customerDTO) {
        if(this.getByID(id) != null){
            Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
            customer.setId(new ObjectIdGenerator().generate().toString());
            Mono<Customer> savedCustomer = customerRepository.save(customer);
            return savedCustomer.map(customerMapper::customerToCustomerDTO);
        }else{
            Mono<CustomerDTO> savedCustomerDTO = this.createNewCustomer(customerDTO);
            return savedCustomerDTO;
        }
    }

    @Override
    public Mono<CustomerDTO> patch(String id, CustomerDTO customerDTO) {

        Customer customer = customerRepository.findById(id).block();

        if (customer != null){

            if(customerDTO.getLastName() != null){
                customer.setLastName(customerDTO.getLastName());
            }

            if(customerDTO.getFirstName() != null){
                customer.setFirstName(customerDTO.getFirstName());
            }

            // Save customer
            Mono<Customer> savedCustomer = customerRepository.save(customer);

            // Convert Customer to CustomerDTO object
            CustomerDTO customerDTOMono = savedCustomer.map(customerMapper::customerToCustomerDTO).block();

            return Mono.just(customerDTOMono);
        }
        else{
            Mono<CustomerDTO> savedCustomerDTO = this.createNewCustomer(customerDTO);
            return savedCustomerDTO;
        }
    }

    @Override
    public Mono<Void> deleteCustomerByID(String id) {
        return customerRepository.deleteById(id);
    }
}
