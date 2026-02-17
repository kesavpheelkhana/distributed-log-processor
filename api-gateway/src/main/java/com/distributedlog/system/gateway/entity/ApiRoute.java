package com.distributedlog.system.gateway.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "api_routes")
public class ApiRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String routeIdentifier;
    
    @Column(nullable = false)
    private String uri;
    
    private String method;
    
    @Column(nullable = false)
    private String path;
}
