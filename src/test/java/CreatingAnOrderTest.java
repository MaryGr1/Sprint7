import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.Order;
import org.example.OrderSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;


import static org.hamcrest.Matchers.notNullValue;


@RunWith(Parameterized.class)

public class CreatingAnOrderTest {
    private Order order;

    OrderSteps orderSteps = new OrderSteps();

    private String[] color;

    public CreatingAnOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters

   public static Object[][] creatingAnOrder()
    {
        return new Object[][] {
                {new String[]{}},
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}}
        };
    }


    @Before

    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        order = new Order();
    }

    @Test

    public void creatingAnOrderTest() throws IOException{
        order.setColor(color);

        orderSteps
                .creatingAnOrder(order)
                .statusCode(201)
                .and()
                .assertThat().body("track",notNullValue());
    }


}



