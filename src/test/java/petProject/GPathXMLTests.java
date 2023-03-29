package petProject;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;
import org.junit.Test;
import petProject.config.PetConfig;
import petProject.config.PetEndpoints;
import petProject.util.Status;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GPathXMLTests extends PetConfig {
    @Test
    public void getPetNameById() {

        Response response = given().pathParam("petId", 3).
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
                when()
                .get(PetEndpoints.SINGLE_PET);

        String id = response.path("Pet.id");
        String name = response.path("Pet.name");

        System.out.println(name);

        assertEquals(id, "3");
    }

    @Test
    public void getListOfXmlNodes() {
        Response response = given().queryParam("status", Status.AVAILABLE.toString().toLowerCase()).
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
                when()
                .get(PetEndpoints.FIND_BY_STATUS);

        String responseAsString = response.asString();

        List<Node> allResult = XmlPath.from(responseAsString).get(
                "ArrayList.item.findAll { element -> return element }"
        );

        System.out.println(allResult.get(2).get("name").toString());
    }
}
