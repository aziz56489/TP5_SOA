package com.example.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        /*return builder.routes()
                .route(r->r.path("/customers/**").uri("http://localhost:8080/").id("r1"))
                .route(r->r.path("/products/**").uri("http://localhost:8081/").id("r2"))
                .build();*/
        return builder.routes()
                .route( "r1",r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                .route("r2",r->r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                .build();

    }

}
