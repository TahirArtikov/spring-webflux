package com.webflux.users.service;

import com.webflux.users.presentation.model.CreateUserRequest;
import com.webflux.users.presentation.model.UserRest;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService extends ReactiveUserDetailsService {
    Mono<UserRest> createUser(Mono<CreateUserRequest> createUserRequestMono);
    Mono<UserRest> getUserById(UUID id, String include, String jwt);
    Flux<UserRest> findAll(int page, int limit);
    Flux<UserRest> streamUser();
}
