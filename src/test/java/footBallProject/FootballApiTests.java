package footBallProject;

import footBallProject.config.FootballConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FootballApiTests extends FootballConfig {

    @Test
    public void getDetailsOfOneArea(){

        int areas = 2255;
        String expectedName = "United Kingdom";

        given()
                .queryParam("areas", areas).
        when()
                .get("areas").
        then()
                .body("areas.name[0]", equalTo(expectedName));
    }

    @Test
    public void getDateFounded(){

        int expectedValue = 1886;

        given().
        when()
                .get("teams/57").
        then()
                .body("founded", equalTo(expectedValue));
    }

    @Test
    public void getFirstTeamName(){

        String expectedLeague = "Campeonato Brasileiro Série A";

        given().
        when()
                .get("competitions/").
        then().
                body("competitions[0].name", equalTo(expectedLeague));
    }

    @Test
    public void getAllTeamData(){
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllTeamData_DoCheckFirst(){
        Response response =
                given().
                when()
                        .get("teams/57").
                then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        String jsonReponseAsString = response.asString();
        System.out.println(jsonReponseAsString);
    }

    @Test
    public void extractHeaders(){
        Response response =
                given().
                when().
                        get("teams/57").
                then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        Headers headers = response.getHeaders();

        String contentType = response.getHeader("Content-Type");

        System.out.println(contentType);
        System.out.println(headers);
    }

    @Test
    public void extractFirstTeamName(){
        String firstTeamName = get("competitions/").jsonPath().getString("competitions[0].name");

        System.out.println(firstTeamName);
    }

    @Test
    public void extractAllTeamNames(){
        Response response =
                given().
                when().get("competitions/").
                then().extract().response();

        List<String> teamNames = response.path("competitions.name");

        for (String teamName : teamNames){
            System.out.println(teamName);
        }
    }

}
