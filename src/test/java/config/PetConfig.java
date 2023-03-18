package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class PetConfig {

    public static RequestSpecification pet_requestSpec;
    public static ResponseSpecification pet_responseSpec;

    @BeforeClass
    public static void setup(){

        pet_requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://petstore3.swagger.io")
                .setBasePath("/api/v3/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        pet_responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.requestSpecification = pet_requestSpec;
        RestAssured.responseSpecification = pet_responseSpec;
    }
}
