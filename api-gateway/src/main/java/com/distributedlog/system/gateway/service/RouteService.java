package com.distributedlog.system.gateway.service;

import com.distributedlog.system.gateway.entity.ApiRoute;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteService {

    public abstract Flux<ApiRoute> getAll();
    public abstract Mono<ApiRoute> create(ApiRoute route);
    public abstract Mono<ApiRoute> getById(String id);

}