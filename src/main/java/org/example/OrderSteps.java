package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderSteps {


    @Step("Send POST request to /api/v1/orders")

    public ValidatableResponse creatingAnOrder(String jsonBody) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody)
                .when()
                .post("/api/v1/orders")
                .then();
    }

    @Step("Send GET request to /api/v1/orders")

    public ValidatableResponse listOfOrdersStatusCode() {
        return given()
                .get("/api/v1/orders")
                .then();
    }
}
