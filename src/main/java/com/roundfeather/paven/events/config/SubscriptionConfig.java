package com.roundfeather.paven.events.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithParentName;

import java.util.Map;

@ConfigMapping(prefix = "app.subscriptions")
public interface SubscriptionConfig {

    @WithParentName
    Map<String, String> subscriptions();
}
