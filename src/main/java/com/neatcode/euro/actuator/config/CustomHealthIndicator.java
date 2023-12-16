package com.neatcode.euro.actuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * /health endpoint is aggregation of multiple health indicators.
 * You can add your own health indicators by extending HealthIndicator interface.
 */
@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().withDetail("custom", "custom health indicator").build();
    }

    private int check() {
        // some logic to check health
        return 0;
    }
}
