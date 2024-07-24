package com.roundfeather.paven.events.controller;

import com.roundfeather.paven.events.config.SubscriptionConfig;
import com.roundfeather.paven.utils.events.repository.EventControlRepository;
import com.roundfeather.paven.utils.http.utils.RequestUtils;
import jakarta.inject.Inject;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("paven/v1/event-control")
public class EventController {

    @Inject
    SubscriptionConfig subscriptionConfig;

    @Inject
    EventControlRepository repository;

    @POST
    public Response cleanEventControl(@HeaderParam("namespace") String namespace, @HeaderParam("eventId") String eventId, @HeaderParam("subscription") String subscription) {
        String userId = RequestUtils.getAuthorizedUser().getUserKey().toString();
        String lockedResource = subscriptionConfig.subscriptions().get(subscription);

        repository.deleteEventControl(namespace, userId, eventId, lockedResource);

        return Response.ok(Map.of("userId", userId, "lockedResource", lockedResource, "eventId", eventId)).build();
    }
}
