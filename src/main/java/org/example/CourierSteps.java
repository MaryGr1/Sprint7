package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierSteps {

    @Step("Send POST request to /api/v1/courier")

    public ValidatableResponse createCourier(String login, String password){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        " \"login\" : \"" + login + "\",\n" +
                        "  \"password\" : \""  + password + "\", \n" +
                        "\"firstName\" : \"saske\" \n" +
                        "}")
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Send POST request to /api/v1/courier/login")

    public ValidatableResponse loginCourier(String login, String password){
       return given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        " \"login\" : \"" + login + "\",\n" +
                        "  \"password\" : \""  + password + "\", \n" +
                        "\"firstName\" : \"saske\" \n" +
                        "}")
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    @Step("Send POST request to /api/v1/courier/{id}")

    public ValidatableResponse deleteCourier(Integer id){
        return given()
                .header("Content-type", "application/json")
                .pathParams("id", id)
                .when()
                .delete("/api/v1/courier/{id}")
                .then();
    }

}
