package com.techie.microservices.order.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
@Slf4j
public class TokenInterceptor {

    @Bean
    public ClientHttpRequestInterceptor bearerTokenInterceptor() {
        return (request, body, execution) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken) {
                final Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
                final String token = jwt.getTokenValue();
                request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            }

            return execution.execute(request, body);
        };
    }
}
