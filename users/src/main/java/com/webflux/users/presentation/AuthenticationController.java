package com.webflux.users.presentation;

import com.webflux.users.presentation.model.AuthenticationRequest;
import com.webflux.users.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@RequestBody Mono<AuthenticationRequest> authenticationRequestMono) {
        return authenticationRequestMono
                .flatMap(authenticationRequest ->
                        authenticationService.authenticate(authenticationRequest.getEmail(),
                                authenticationRequest.getPassword()))
                .map(authenticationResultMap-> ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "
                                + authenticationResultMap.get("token"))
                        .header("UserId",authenticationResultMap.get("userId"))
                        .build());
    }
}
