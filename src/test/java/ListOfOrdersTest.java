import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ListOfOrdersTest {

    @Before

    public void setUp(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    // проверка статуса ответа и возврата списка заказов
    @Test

    public void listOfOrdersStatusCodeTest(){
        Response response =  given()
                .get("/api/v1/orders");
                response.then().statusCode(200)
                        .and()
                        .assertThat().body("orders",notNullValue());
        System.out.println(response.getBody().asString());
    }


}
