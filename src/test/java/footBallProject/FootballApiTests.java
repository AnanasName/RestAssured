package footBallProject;

import footBallProject.config.FootballConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FootballApiTests extends FootballConfig {

    @Test
    public void getDetailsOfOneArea(){
        given()
                .queryParam("areas", 2255).
        when()
                .get("areas");
    }

    @Test
    public void getDateFounded(){
        given().
        when()
                .get("teams/57").
        then()
                .body("founded", equalTo(1886));
    }

    @Test
    public void getFirstTeamName(){
        given().
        when()
                .get("competitions/").
        then().
                body("competitions[0].name", equalTo("Campeonato Brasileiro Série A"));
    }
}
