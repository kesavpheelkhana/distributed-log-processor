package com.distributedlog.system.gateway.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Getter
@Setter
@Document
public class ApiRoute {
    @Id
    private String id;
    private String routeIdentifier;
    private String uri;
    private String method;
    private String path;
}
