package footBallProject;

import footBallProject.config.FootballErrorConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.internal.http.Status.FAILURE;
import static org.junit.Assert.assertTrue;

public class FootballApiErrorTests extends FootballErrorConfig {
    @Test
    public void getDetailsOfOneArea_failUnknownArea(){

        int areas = 22551123;

        Response response = given()
                .queryParam("areas", areas).
                when()
                .get("areas");

        assertTrue(FAILURE.matches(response.getStatusCode()));
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
                        .get("teams/123451324").
                        then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        assertTrue(FAILURE.matches(response.getStatusCode()));
    }

    @Test
    public void findPersonById_failUnknownId() {

        int personId = 23412424;

        Response response =
                given().pathParam("id", personId).
                        when().get("persons/{id}");

        assertTrue(FAILURE.matches(response.getStatusCode()));
    }
}
