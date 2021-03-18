package com.ciandt.pauloneto.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;


@Liveness
@ApplicationScoped
public class LivenessHealth implements HealthCheck{

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder().name("Liveness Probe").up().build();
    }
    
}
