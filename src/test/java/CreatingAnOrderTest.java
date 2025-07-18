import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.example.OrderSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(Parameterized.class)

public class CreatingAnOrderTest {
    private final String jsonFile;

    OrderSteps orderSteps = new OrderSteps();

    public CreatingAnOrderTest(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    @Parameterized.Parameters

   public static Object[][] creatingAnOrder()
    {
        return new Object[][] {
                {"src/test/resources/OrderBlack.json"},
                {"src/test/resources/OrderGrey.json"},
                {"src/test/resources/OrderGreyAndBlack.json"},
                {"src/test/resources/OrderNoColor.json"}
        };
    }

    private String readJsonFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    @Before

    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test

    public void creatingAnOrderTest() throws IOException{
        String jsonBody = readJsonFromFile(jsonFile);
        orderSteps
                .creatingAnOrder(jsonBody)
                .statusCode(201)
                .and()
                .assertThat().body("track",notNullValue());
    }


}



