package com.distributedlog.system.gateway.config;

import com.distributedlog.system.gateway.config.filter.RequestResponseLogGlobalFilter;
import com.distributedlog.system.gateway.service.ApiRouteLocationImpl;
import com.distributedlog.system.gateway.service.RouteService;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    private RequestResponseLogGlobalFilter requestAndResponseLogGlobalFilter;
    //this is to set the values programatically
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("log-producer", r -> r.path("/api/logs/**")
                        .uri("http://localhost:8081"))
                .route("log-consumer-health", r -> r.path("/consumer/health")
                        .uri("http://localhost:8082"))
                .build();
    }

//    @Bean
//    public RouteLocator routeLocator(RouteService routeService, RouteLocatorBuilder routeLocationBuilder) {
//        return new ApiRouteLocationImpl(routeLocationBuilder, routeService);
//    }

}
