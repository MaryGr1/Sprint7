import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.*;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatingACourierErrorTest {

    CourierSteps courierSteps = new CourierSteps();
    private CourierNoLogin courierNoLogin;
    private CourierNoPassword courierNoPassword;
    private CourierNoFirstName courierNoFirstName;

    @Before

    public void setUp(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courierNoLogin = new CourierNoLogin();
        courierNoLogin.setPassword(RandomStringUtils.randomAlphabetic(12));
        courierNoPassword = new CourierNoPassword();
        courierNoPassword.setLogin(RandomStringUtils.randomAlphabetic(12));
        courierNoFirstName = new CourierNoFirstName();
    }

    //создание без логина

    @Test

    public void creationWithoutALoginTest() {
        courierSteps
                .createCourierNoLogin(courierNoLogin)
                .statusCode(400);
    }

    //создание без пароля

    @Test

    public void creationWithoutAPasswordTest() {
        courierSteps
                .createCourierNoPassword(courierNoPassword)
                .statusCode(400);
    }

    //создание без имени курьера

    @Test

    public void creationWithoutAFirstNameTest() {
       courierSteps
               .createCourierNoFirstName(courierNoFirstName)
               .statusCode(400);
    }

}
