package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierSteps {



    @Step("Send POST request to /api/v1/courier")

    public ValidatableResponse createCourier(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Send POST request to /api/v1/courier/login")

    public ValidatableResponse loginCourier(Courier courier){
       return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    @Step("Send POST request to /api/v1/courier/login без логина")

    public ValidatableResponse loginCourierNoLogin(CourierNoLogin courierNoLogin){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierNoLogin)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    @Step("Send POST request to /api/v1/courier/login без пароля")

    public ValidatableResponse loginCourierNoPassword(CourierNoPassword courierNoPassword){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierNoPassword)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

   @Step("Send POST request to /api/v1/courier/login несуществующий логин")

   public ValidatableResponse loginCourierErrorLogin(Courier courier){
       return given()
               .header("Content-type", "application/json")
               .and()
               .body(courier)
               .when()
               .post("/api/v1/courier/login")
               .then();
    }

    @Step("Send POST request to /api/v1/courier/login несуществующий пароль")

    public ValidatableResponse loginCourierErrorPassword(Courier courier){
    return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    @Step("Send POST request to /api/v1/courier создание без логина")

    public ValidatableResponse createCourierNoLogin(CourierNoLogin courierNoLogin){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierNoLogin)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Send POST request to /api/v1/courier создание без пароля")

    public ValidatableResponse createCourierNoPassword(CourierNoPassword courierNoPassword){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierNoPassword)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Send POST request to /api/v1/courier создание без имени пользователя")

    public ValidatableResponse createCourierNoFirstName(CourierNoFirstName courierNoFirstName){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierNoFirstName)
                .when()
                .post("/api/v1/courier")
                .then();
    }


    @Step("Send POST request to /api/v1/courier/{id}")

    public ValidatableResponse deleteCourier(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .pathParams("id", courier.getId())
                .when()
                .delete("/api/v1/courier/{id}")
                .then();
    }

}
