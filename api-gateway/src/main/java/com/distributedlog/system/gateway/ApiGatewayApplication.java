package com.distributedlog.system.gateway;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationContextEvent;

@SpringBootApplication
@AllArgsConstructor
public class ApiGatewayApplication  {

    ApplicationEventPublisher applicationEventPublisher;

	public static void main(String[] args) {

        SpringApplication.run(ApiGatewayApplication.class, args);
	}

    /**
     * Refresh the routes to load from data store
     */
//    public void refreshRoutes() {
//        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
//    }
}
