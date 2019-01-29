package com.mesarikaya.restapiwithspringwebflux.Repositories;

import com.mesarikaya.restapiwithspringwebflux.models.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveMongoRepository<Category, Long> {

    Flux<Category> findByName(String name);
    Mono<Category> findById(String id);
    Mono<Void> deleteById(String id);
}
