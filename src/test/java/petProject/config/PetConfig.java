package petProject.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;
import static petProject.config.Constants.BASE_PATH;
import static petProject.config.Constants.BASE_URL;

public class PetConfig {

    public static RequestSpecification pet_requestSpec;
    public static ResponseSpecification pet_responseSpec;

    @BeforeClass
    public static void setup(){

        pet_requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        pet_responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();

        RestAssured.requestSpecification = pet_requestSpec;
        RestAssured.responseSpecification = pet_responseSpec;
    }
}
