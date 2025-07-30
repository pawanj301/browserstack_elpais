package utils;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import config.APIConfig;

public class RequestBuilder {
    public static RequestSpecification getRequestSpec(String body) {
        return given()
                .header("X-RapidAPI-Host", APIConfig.get("api.host"))
                .header("x-rapidapi-key", APIConfig.get("api.key"))
                .header("Content-Type", "application/json")
                .body(body);
    }
}
