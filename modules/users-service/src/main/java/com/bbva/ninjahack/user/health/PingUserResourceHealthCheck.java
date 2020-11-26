package com.bbva.ninjahack.user.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import com.bbva.ninjahack.user.UserResource;

@Liveness
@ApplicationScoped
public class PingUserResourceHealthCheck implements HealthCheck {

    @Inject
    UserResource userResource;

    @Override
    public HealthCheckResponse call() {
        userResource.hello();
        return HealthCheckResponse.named("Ping User REST Endpoint").up().build();
    }
}
