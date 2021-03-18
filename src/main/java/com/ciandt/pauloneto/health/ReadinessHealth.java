package com.ciandt.pauloneto.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadinessHealth implements HealthCheck{

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder().name("Readiness Probe").up().build();
    }
    
}
