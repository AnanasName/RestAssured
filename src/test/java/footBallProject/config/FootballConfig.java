package footBallProject.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static footBallProject.config.Constants.*;

public class FootballConfig {

    public static RequestSpecification football_requestSpec;
    public static ResponseSpecification football_responseSpec;

    @BeforeClass
    public static void setup(){

        football_requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH)
                .addHeader("X-Auth-Token", API_KEY)
                .addHeader("X-Response-Control", "minified")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        football_responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.requestSpecification = football_requestSpec;
        RestAssured.responseSpecification = football_responseSpec;
    }
}
