import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.CourierSteps;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatingACourierErrorTest {

    public String login = RandomStringUtils.randomAlphabetic(12);
    public String password = RandomStringUtils.randomAlphabetic(12);
    CourierSteps courierSteps = new CourierSteps();

    @Before

    public void setUp(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    //создание без логина

    @Test

    public void creationWithoutALoginTest() {
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        "  \"password\" : \""  + password + "\", \n" +
                        "\"firstName\" : \"saske\" \n" +
                        "}")
                .when()
                .post("/api/v1/courier")
                .then().statusCode(400);
    }

    //создание без пароля

    @Test

    public void creationWithoutAPasswordTest() {
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        " \"login\" : \"" + login + "\",\n" +
                        "\"firstName\" : \"saske\" \n" +
                        "}")
                .when()
                .post("/api/v1/courier")
                .then().statusCode(400);
    }

    //создание без имени курьера

    @Test

    public void creationWithoutAFirstNameTest() {
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        " \"login\" : \"" + login + "\",\n" +
                        "  \"password\" : \""  + password + "\", \n" +
                        "}")
                .when()
                .post("/api/v1/courier")
                .then().statusCode(400);
    }

}
