package footBallProject;

import footBallProject.config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void extractMapOfObjectWithFindAndFindAll(){
        Response response = get("teams/57");
        Map<String, ?> playerOfCertainPosition = response.path(
                "squad.findAll { it.position == 'Defender'}.find { it.nationality == 'Greece' }"
        );
        System.out.println("Details of players: " + playerOfCertainPosition);
    }

    @Test
    public void extractMapOfObjectWithFindAndFindAllWithParameters(){
        String position = "Defender";
        String nationality = "Greece";

        Response response = get("teams/57");
        Map<String, ?> playerOfCertainPosition = response.path(
                "squad.findAll { it.position == '%s' }.find { it.nationality == '%s'}",
                position, nationality
        );

        System.out.println("Details of players: " + playerOfCertainPosition);
    }

    @Test
    public void extractMultiplePlayers(){

        String position = "Midfielder";
        String nationality = "England";

        Response response = get("teams/57");
        ArrayList<Map<String, ?>> allPlayersCertainNation = response.path(
                "squad.findAll { it.position == '%s'}.findAll { it.nationality == '%s'}",
                position, nationality
        );

        System.out.println("All players: " + allPlayersCertainNation);
    }
}
