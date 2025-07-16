import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.CourierSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CouriersLoginTest {

    public String login = RandomStringUtils.randomAlphabetic(12);
    public String password = RandomStringUtils.randomAlphabetic(12);
    CourierSteps courierSteps = new CourierSteps();

    @Before

    public void setUp(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }

    // проверка возможности авторизации + проверка ответа

    @Test

    public void successfulAuthorizationTest(){

        courierSteps
                .createCourier(login, password);

       courierSteps
               .loginCourier(login, password)
                .statusCode(200)
                .and()
                .assertThat().body("id",notNullValue());
    }

    // авторизация без логина

    @Test

    public void authorizationWithoutALogin(){
        courierSteps
                .createCourier(login, password);

        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        "  \"password\" : \""  + password + "\", \n" +
                        "\"firstName\" : \"saske\" \n" +
                        "}")
                .when()
                .post("/api/v1/courier/login")
                .then().statusCode(400);
    }

    // авторизация без пароля (вечный спиннер!!! в постман так же (ошибка 504)

    @Test

    public void authorizationWithoutAPassword(){
        courierSteps
                .createCourier(login, password);

    given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        " \"login\" : \"" + login + "\",\n" +
                        "\"firstName\" : \"saske\" \n" +
                        "}")
                .when()
                .post("/api/v1/courier/login")
                .then().statusCode(400);
}

    // несуществующий логин

    @Test

    public void nonExistentLoginTest(){
        courierSteps
                .createCourier(login, password);

        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        " \"login\" : \" kjhgfd\" ,\n" +
                        "  \"password\" : \""  + password + "\", \n" +
                        "\"firstName\" : \"saske\" \n" +
                        "}")
                .when()
                .post("/api/v1/courier/login")
                .then().statusCode(404);
    }


    //несуществующий пароль

    @Test

    public void nonExistentPasswordTest(){
        courierSteps
                .createCourier(login, password);

        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        " \"login\" : \"" + login + "\",\n" +
                        " \"password\" : \" kjhgfd\" ,\n" +
                        "\"firstName\" : \"saske\" \n" +
                        "}")
                .when()
                .post("/api/v1/courier/login")
                .then().statusCode(404);
    }


   @After

    public void tearDown() {
        Integer id = courierSteps.loginCourier(login, password)
                .extract().body().path("id");
        courierSteps.deleteCourier(id);

    }
}
