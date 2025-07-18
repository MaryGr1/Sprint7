import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.Courier;
import org.example.CourierNoLogin;
import org.example.CourierNoPassword;
import org.example.CourierSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class CouriersLoginTest {

    CourierSteps courierSteps = new CourierSteps();
    private Courier courier;
    private CourierNoLogin courierNoLogin;
    private CourierNoPassword courierNoPassword;

    @Before

    public void setUp(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(12));
        courier.setPassword(RandomStringUtils.randomAlphabetic(12));
        courierNoLogin = new CourierNoLogin();
        courierNoLogin.setPassword(RandomStringUtils.randomAlphabetic(12));
        courierNoPassword = new CourierNoPassword();
        courierNoPassword.setLogin(RandomStringUtils.randomAlphabetic(12));
        courierSteps
                .createCourier(courier);

    }

    // проверка возможности авторизации + проверка ответа

    @Test

    public void successfulAuthorizationTest(){

       courierSteps
               .loginCourier(courier)
                .statusCode(200)
                .and()
                .assertThat().body("id",notNullValue());
    }

    // авторизация без логина

    @Test

    public void authorizationWithoutALogin(){

       courierSteps
               .loginCourierNoLogin(courierNoLogin)
                .statusCode(400);
    }

    // авторизация без пароля (вечный спиннер!!! в постман так же (ошибка 504)

    @Test

    public void authorizationWithoutAPassword(){

courierSteps
        .loginCourierNoPassword(courierNoPassword)
        .statusCode(400);
}

    // несуществующий логин

    @Test

    public void nonExistentLoginTest(){

        Courier fakeCourier = new Courier();
        fakeCourier.setLogin("nonExistentLogin");
        fakeCourier.setPassword(courier.getPassword());
        courierSteps
        .loginCourierErrorLogin(fakeCourier)
        .statusCode(404);
    }


    //несуществующий пароль

    @Test

    public void nonExistentPasswordTest(){

        Courier fakeCourier = new Courier();
        fakeCourier.setLogin(courier.getLogin());
        fakeCourier.setPassword("nonExistentPassword");

        courierSteps
                .loginCourierErrorPassword(fakeCourier)
        .statusCode(404);
    }


   @After

    public void tearDown() {
        Integer id = courierSteps.loginCourier(courier)
                .extract().body().path("id");
       courier.setId(id);
        courierSteps.deleteCourier(courier);

    }
}
