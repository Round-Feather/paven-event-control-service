package com.roundfeather.paven.events.controller;

import com.roundfeather.paven.utils.events.repository.EventControlRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class EventControllerTest {

    @InjectMock
    EventControlRepository repository;

    @Test
    void testEventRequest() {

        String body = "{\n" +
                "      \"message\": {\n" +
                "          \"attributes\": { \n" +
                "              \"key\": \"value\",\n" +
                "              \"tenant\": \"tenant\",\n" +
                "              \"spaceId\": \"spaceId\",\n" +
                "              \"namespace\": \"namespace\",\n" +
                "              \"platform\": \"mobile\",\n" +
                "              \"os\": \"ios\",\n" +
                "              \"X-Apigateway-Api-Userinfo\": \"eyJuYW1lIjoibGF1cmEuY2hhY29uQGdsb2JhbnQuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyLzQ5ZGJlZDMzYjFmOGRhOGNiM2VlYjQ1NTUzYjQzNzNjP3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGbGEucG5nIiwidXNlcklkIjoiZmZkMmI4MjQtOGU4MC00ZmU4LWI0MzctMGNmZDRjOTZmZjAzIiwidXNlcktleSI6NDk5MzY1NTUzODQ1MDQzMiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL3BhdmVuLWNvbW1vbi12MyIsImF1ZCI6InBhdmVuLWNvbW1vbi12MyIsImF1dGhfdGltZSI6MTY1NzIwOTU0NywidXNlcl9pZCI6IkFGSnQ2cHhpQW5Wb0pKMjM5Tmw0QlBiakFNYTIiLCJzdWIiOiJBRkp0NnB4aUFuVm9KSjIzOU5sNEJQYmpBTWEyIiwiaWF0IjoxNjU3MjI0NzYxLCJleHAiOjE2NTcyMjgzNjEsImVtYWlsIjoibGF1cmEuY2hhY29uQGdsb2JhbnQuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsib2lkYy5mYWNlYm9vay1kZXYtYXV0aDAiOlsiZW1haWx8NjI3OTM3MzEwMTljZmRhODViYTRiN2VlIl0sImVtYWlsIjpbImxhdXJhLmNoYWNvbkBnbG9iYW50LmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Im9pZGMuZmFjZWJvb2stZGV2LWF1dGgwIiwic2lnbl9pbl9hdHRyaWJ1dGVzIjp7InVwZGF0ZWRfYXQiOiIyMDIyLTA3LTA3VDE1OjU5OjAzLjU5N1oifSwidGVuYW50IjoiZmFjZWJvb2stZGV2LXYzLXRjamc0In19\",\n" +
                "              \"eventId\": \"1234\"\n" +
                "          },\n" +
                "          \"data\": \"eyJrZXkiOiAidmFsdWUifQ==\",\n" +
                "          \"messageId\": \"2070443601311540\",\n" +
                "          \"message_id\": \"2070443601311540\",\n" +
                "          \"orderingKey\": \"key\",\n" +
                "          \"publishTime\": \"2021-02-26T19:13:55.749Z\",\n" +
                "          \"publish_time\": \"2021-02-26T19:13:55.749Z\"\n" +
                "      },\n" +
                "    \"subscription\": \"projects/myproject/subscriptions/mysubscription\"\n" +
                "}";

        io.restassured.response.Response response = given()
                .when()
                .body(body)
                .post("paven/v1/event-control");

        response.then()
                .statusCode(200);

        Map<String, String> res = response.jsonPath()
                .getMap(".");

        assertEquals(Map.of("eventId", "1234", "lockedResource", "calculations", "userId", "4993655538450432"), res);
    }
}
