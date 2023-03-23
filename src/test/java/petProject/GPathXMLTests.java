package petProject;

import io.restassured.response.Response;
import org.junit.Test;
import petProject.config.PetConfig;
import petProject.config.PetEndpoints;

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

        String name = response.path("Pet.name");
        String id = response.path("Pet.id");

        assertEquals(name, "test");
        assertEquals(id, "3");
    }


}
