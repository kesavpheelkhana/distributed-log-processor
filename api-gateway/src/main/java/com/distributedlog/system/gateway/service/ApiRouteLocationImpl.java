package com.distributedlog.system.gateway.service;

import com.distributedlog.system.gateway.entity.ApiRoute;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
//@Service
public class ApiRouteLocationImpl
//        implements RouteLocator
{

//    private final RouteLocatorBuilder routeLocatorBuilder;
//    private final RouteService routeService;

//    @Override
//    public Flux<Route> getRoutes() {
//        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
//        return routeService.getAll().map(apiRoute -> routesBuilder.route(String.valueOf(apiRoute.getRouteIdentifier()),
//                predicateSpec -> setPredicateSpec(apiRoute,predicateSpec)))
//                .collectList()
//                .flatMapMany(builders -> routesBuilder.build().getRoutes());
//    }

//    private Buildable<Route> setPredicateSpec(ApiRoute apiRoute, PredicateSpec predicateSpec) {
//        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getPath());
//        if (!StringUtils.isEmpty(apiRoute.getMethod())) {
//            booleanSpec.and().method(apiRoute.getMethod());
//        }
//        return booleanSpec.uri(apiRoute.getUri());
//    }
}
