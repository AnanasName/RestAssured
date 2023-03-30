package petStoreProject.pet;

import io.restassured.response.Response;
import org.junit.Test;
import petStoreProject.pet.config.PetEndpoints;
import petStoreProject.pet.config.PetErrorConfig;
import petStoreProject.pet.pojo.Pet;
import petStoreProject.pet.util.Status;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.internal.http.Status.FAILURE;
import static org.junit.Assert.*;

public class PetErrorTest extends PetErrorConfig {
    @Test
    public void testPetSerializationByJSON_failEmptyData() {

        Response response = given().
                body("").
                when().
                post(PetEndpoints.DEFAULT_PET_PATH);

        assertTrue(FAILURE.matches(response.getStatusCode()));
    }

    @Test
    public void updatePetByJson_failEmptyData() {

        Response response = given()
                .body("").
                when()
                .post(PetEndpoints.DEFAULT_PET_PATH);

        assertTrue(FAILURE.matches(response.getStatusCode()));
    }

    @Test
    public void deletePet_failIncorrectId() {

        Response response = given().
                pathParam("petId", "aaa").
                when()
                .delete(PetEndpoints.SINGLE_PET);

        assertTrue(FAILURE.matches(response.getStatusCode()));
    }

    @Test
    public void getSinglePet_failUnknownId() {

        int petId = 324345298;

        Response response = given()
                .pathParam("petId", petId).
                when()
                .get(PetEndpoints.SINGLE_PET);

        assertTrue(FAILURE.matches(response.getStatusCode()));
    }

    @Test
    public void findPetsByTag_emptyList() {

        List<String> tags = new ArrayList<>();
        tags.add("dsfssadfqewewr");

        Response response = given().queryParam("tags", tags).
                when()
                .get(PetEndpoints.FIND_BY_TAGS);

        Pet[] pets = response.as(Pet[].class);

        assertEquals(0, pets.length);
    }

    @Test
    public void updatePetById_failUnknownId() {

        int petId = 323423523;
        String petName = "Jame";
        String petStatus = Status.AVAILABLE.toString().toLowerCase();

        Response response = given().pathParam("petId", petId).queryParam("name", petName).queryParam("status", petStatus).
                when().
                post(PetEndpoints.SINGLE_PET);

        assertTrue(FAILURE.matches(response.getStatusCode()));
    }
}
