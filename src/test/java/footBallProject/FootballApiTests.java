package footBallProject;

import footBallProject.config.FootballConfig;
import footBallProject.pojo.Person;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FootballApiTests extends FootballConfig {

    @Test
    public void getDetailsOfOneArea() {

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
    public void getDateFounded() {

        int expectedValue = 1886;

        given().
                when()
                .get("teams/57").
                then()
                .body("founded", equalTo(expectedValue));
    }

    @Test
    public void getFirstTeamName() {

        String expectedLeague = "Campeonato Brasileiro Série A";

        given().
                when()
                .get("competitions/").
                then().
                body("competitions[0].name", equalTo(expectedLeague));
    }

    @Test
    public void getAllTeamData() {
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllTeamData_DoCheckFirst() {

        String clubName = "Arsenal";

        Response response =
                given().
                        when()
                        .get("teams/57").
                        then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        String jsonReponseAsString = response.asString();
        assertTrue(jsonReponseAsString.contains(clubName));
    }

    @Test
    public void extractHeaders() {

        String contentTypeHeader = "application/json;charset=UTF-8";

        Response response =
                given().
                        when().
                        get("teams/57").
                        then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        String contentType = response.getHeader("Content-Type");

        assertEquals(contentType, contentTypeHeader);
    }

    @Test
    public void extractFirstTeamName() {
        String firstTeamName = get("competitions/").jsonPath().getString("competitions[0].name");

        System.out.println(firstTeamName);
    }

    @Test
    public void extractAllTeamNames() {

        boolean flag = false;

        String expectedLeagueName = "Bundesliga";

        Response response =
                given().
                        when().get("competitions/").
                        then().extract().response();

        List<String> leagueNames = response.path("competitions.name");

        for (String teamName : leagueNames) {
            if (teamName.equals(expectedLeagueName))
                flag = true;
            System.out.println(teamName);
        }

        assertTrue(flag);
    }

    @Test
    public void findPersonById() {

        int personId = 44;

        Response response =
                given().pathParam("id", personId).
                        when().get("persons/{id}");

        Person person = response.as(Person.class);

        assertEquals("Cristiano Ronaldo", person.getName());
    }

    @Test
    public void getStandingsForLeague(){
        String league = "PL";

        Response response =
                given().pathParam("id", league).
                        when().get("competitions/{id}/standings");

        Person person = response.as(Person.class);

        assertEquals("Cristiano Ronaldo", person.getName());
    }
}
