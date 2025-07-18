import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.example.OrderSteps;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ListOfOrdersTest {

    OrderSteps orderSteps = new OrderSteps();

    @Before

    public void setUp(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    // проверка статуса ответа и возврата списка заказов
    @Test

    public void listOfOrdersStatusCodeTest(){
        orderSteps
                .listOfOrdersStatusCode()
                .statusCode(200)
                        .and()
                        .assertThat().body("orders",notNullValue());
    }


}
