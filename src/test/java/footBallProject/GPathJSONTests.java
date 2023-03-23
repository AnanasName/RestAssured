package footBallProject;

import footBallProject.config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends FootballConfig {

    @Test
    public void extractMapOfElementsWithFind(){

        Response response = get("competitions/");

        Map<String, ?> allTeamDataForSingleTeam = response.path("competitions.find { it.name == 'Serie A' }");

        System.out.println("Map of Team Data = " + allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind(){
        Response response = get("teams/57");
        String certainPlayer = response.path("squad.find {it.nationality == 'Japan'}.name");
        System.out.println("Name of player = " + certainPlayer);
    }

    @Test
    public void extractListOfValuesWithFindAll(){
        Response response = get("teams/57");
        List<String> playerNames = response.path("squad.findAll {it.position == 'Defence'}.name");
        System.out.println("List of players" + playerNames);
    }

    @Test
    public void extractSingleValueWithHighestNumber(){
        Response response = get("teams/57");
        String playerName = response.path("squad.max { it.shirtNumber }.name");
        System.out.println("Player with highest shirt number = " + playerName);
    }

    @Test
    public void extractMultipleValuesAndSumThen(){
        Response response = get("teams/57");
        int sumOfIds = response.path("squad.collect { it.id }.sum()");
        System.out.println("Sum of all ID = " + sumOfIds);
    }
}
