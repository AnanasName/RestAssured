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

        int petId = 3;

        Response response = given().pathParam("petId", petId).
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
                when()
                .get(PetEndpoints.SINGLE_PET);

        String id = response.path("Pet.id");
        String name = response.path("Pet.name");

        System.out.println(name);

        assertEquals(id, petId);
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

    @Test
    public void getSingleNode() {
        Response response = given().queryParam("status", Status.PENDING.toString().toLowerCase()).
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
                when()
                .get(PetEndpoints.FIND_BY_STATUS);

        String responseAsString = response.asString();

        String itemName = "Dog 3";

        Node pet = XmlPath.from(responseAsString).get(
                "ArrayList.item.find { pet -> def name = pet.name; name == '" + itemName + "' }"
        );

        String petName = pet.get("name").toString();

        System.out.println(petName);

        assertEquals(itemName, petName);
    }

    @Test
    public void getSingleElementDepthFirst() {
        Response response = given().queryParam("status", Status.SOLD.toString().toLowerCase()).
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
                when()
                .get(PetEndpoints.FIND_BY_STATUS);

        String responseAsString = response.asString();

        String itemName = "Dog 2";

        String photoUrl_1 = XmlPath.from(responseAsString).getString(
                "**.find { it.name ==  '" + itemName + "' }.photoUrls.photoUrl[0]"
        );

        System.out.println(photoUrl_1);

        assertEquals("url1", photoUrl_1);
    }

    @Test
    public void getAllNodesBasedOnCondition() {
        Response response = given().queryParam("status", Status.AVAILABLE.toString().toLowerCase()).
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
                when()
                .get(PetEndpoints.FIND_BY_STATUS);

        String responseAsString = response.asString();

        Long conditionId = 10L;

        List<Node> allPetsOverCertainId = XmlPath.from(responseAsString).get(
                "ArrayList.item.findAll { it.id.toLong() >= " + conditionId + "}"
        );

        System.out.println(allPetsOverCertainId);
    }
}
