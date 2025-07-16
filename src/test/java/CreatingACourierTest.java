import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.CourierSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.is;

public class CreatingACourierTest {
    public String login = RandomStringUtils.randomAlphabetic(12);
    public String password = RandomStringUtils.randomAlphabetic(12);
    CourierSteps courierSteps = new CourierSteps();

    @Before

    public void setUp(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    // создание курьера + проверка ответа
    @Test

    public void creatingACourierStatusCodeTest() {

        courierSteps
                .createCourier(login, password)
                .statusCode(201)
                .body("ok",is(true));
    }

    //создание курьера с теми же данными
    @Test

    public void creatingIdenticalCouriersTest() {
        courierSteps
                .createCourier(login, password);
        courierSteps
                .createCourier(login, password)
        .statusCode(409);
    }


    @After

    public void tearDown() {
        Integer id = courierSteps.loginCourier(login, password)
                .extract().body().path("id");
        courierSteps.deleteCourier(id);

    }

}
