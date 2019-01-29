package com.mesarikaya.restapiwithspringwebflux.Repositories;

import com.mesarikaya.restapiwithspringwebflux.models.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, Long> {

    Flux<Customer> findByLastName(String lastName);
    Mono<Customer> findById(String id);
    Mono<Void> deleteById(String id);
}
