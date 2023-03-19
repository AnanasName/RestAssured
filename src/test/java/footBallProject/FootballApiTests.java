package footBallProject;

import footBallProject.config.FootballConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FootballApiTests extends FootballConfig {

    @Test
    public void getDetailsOfOneArea(){
        given()
                .queryParam("areas", 2255).
        when()
                .get("areas");
    }
}
