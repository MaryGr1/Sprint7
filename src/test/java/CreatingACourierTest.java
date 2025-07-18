import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.Courier;
import org.example.CourierSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.is;

public class CreatingACourierTest {
    private Courier courier;
    CourierSteps courierSteps = new CourierSteps();

    @Before

    public void setUp(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(12));
        courier.setPassword(RandomStringUtils.randomAlphabetic(12));
    }

    // создание курьера + проверка ответа
    @Test

    public void creatingACourierStatusCodeTest() {

        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok",is(true));
    }

    //создание курьера с теми же данными
    @Test

    public void creatingIdenticalCouriersTest() {
        courierSteps
                .createCourier(courier);
        courierSteps
                .createCourier(courier)
        .statusCode(409);
    }


    @After

    public void tearDown() {
        Integer id = courierSteps.loginCourier(courier)
                .extract().body().path("id");
        courier.setId(id);
        courierSteps.deleteCourier(courier);

    }

}
