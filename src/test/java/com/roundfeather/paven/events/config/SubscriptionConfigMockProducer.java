package com.roundfeather.paven.events.config;

import io.smallrye.config.SmallRyeConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.Config;

public class SubscriptionConfigMockProducer {
    @Inject
    Config config;

    @Produces
    @ApplicationScoped
    @io.quarkus.test.Mock
    SubscriptionConfig subscriptionConfig() {
        return config.unwrap(SmallRyeConfig.class).getConfigMapping(SubscriptionConfig.class);
    }
}
