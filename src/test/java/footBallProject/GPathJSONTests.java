package footBallProject;

import footBallProject.config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends FootballConfig {

    @Test
    public void extractMapOfElementsWithFind(){

        Response response = get("competitions/");

        Map<String, ?> allTeamDataForSingleTeam = response.path("competitions.find { it.name == 'Serie A' }");

        System.out.println("Map of Team Data = " + allTeamDataForSingleTeam);
    }
}
