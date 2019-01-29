package com.mesarikaya.restapiwithspringwebflux.Repositories;

import com.mesarikaya.restapiwithspringwebflux.models.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, Long> {

    Flux<Vendor> findByLastName(String lastName);
    Mono<Vendor> findById(String id);
    Mono<Void> deleteById(String id);
}
