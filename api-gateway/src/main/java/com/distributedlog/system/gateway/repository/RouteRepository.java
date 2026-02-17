package com.distributedlog.system.gateway.repository;

import com.distributedlog.system.gateway.entity.ApiRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<ApiRoute, Long> {
}
