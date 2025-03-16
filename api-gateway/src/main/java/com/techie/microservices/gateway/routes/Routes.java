package com.techie.microservices.gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class Routes {

    @Value("${product.service.name}")
    private String productServiceName;

    @Value("${order.service.name}")
    private String orderServiceName;

    @Value("${inventory.service.name}")
    private String inventoryServiceName;

    @Bean
    public RouterFunction<ServerResponse> productServicesRoute() {
        return route("product_service")
                .route(path("/api/v1/products/**"), http())
                .filter(lb(productServiceName))
                .filter(circuitBreaker(config -> config.setId("productServiceCircuitBreaker").setFallbackUri("forward:/fallbackRoute")))
                .build();
    }

    // filter http://localhost:8080/aggregate/product-service/v3/api-docs -> http://localhost:8080/api-docs
    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
        return route("product_service_swagger")
                .route(path("/aggregate/product-service/v3/api-docs"), http())
                .filter(lb(productServiceName))
                .filter(circuitBreaker("productServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServicesRoute() {
        return route("order_service")
                .route(path("/api/v1/orders/**"), http())
                .filter(lb(orderServiceName))
                .filter(circuitBreaker(config -> config.setId("orderServiceCircuitBreaker").setFallbackUri("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
        return route("order_service_swagger")
                .route(path("/aggregate/order-service/v3/api-docs"), http())
                .filter(lb(orderServiceName))
                .filter(circuitBreaker("orderServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServicesRoute() {
        return route("inventory_service")
                .route(path("api/v1/inventory/**"), http())
                .filter(lb(inventoryServiceName))
                .filter(circuitBreaker(config -> config.setId("inventoryServiceCircuitBreaker").setFallbackUri("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
        return route("inventory_service_swagger")
                .route(path("/aggregate/inventory-service/v3/api-docs"), http())
                .filter(lb(inventoryServiceName))
                .filter(circuitBreaker("inventoryServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service Unavailable, Please  try again later"))
                .build();
    }
}
