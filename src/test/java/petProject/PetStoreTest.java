package petProject;

import io.restassured.response.Response;
import petProject.config.PetConfig;
import petProject.config.PetEndpoints;
import org.junit.Test;
import petProject.pojo.Category;
import petProject.pojo.Pet;
import petProject.pojo.Tag;
import petProject.util.Status;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.internal.http.Status.SUCCESS;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static petProject.util.Constants.PET_JSON_SCHEMA;
import static petProject.util.Constants.PET_XSD;

public class PetStoreTest extends PetConfig {

    @Test
    public void testPetSerializationByJSON() {

        int newPetId = 13;

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("strings");

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(0L, "string"));

        Pet pet = new Pet(new Category(1L, "cat"), (long) newPetId, "doggie", photoUrls, "available", tags);

        given().
                body(pet).
                when().
                post(PetEndpoints.DEFAULT_PET_PATH)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(newPetId));
    }

    @Test
    public void updatePetByJson() {

        String newName = "Maggie";

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("strings");

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(0L, "string"));

        Pet pet = new Pet(new Category(1L, "cat"), 13L, newName, photoUrls, "send", tags);

        given()
                .body(pet).
                when()
                .post(PetEndpoints.DEFAULT_PET_PATH).
                then()
                .statusCode(is(200))
                .body("name", equalTo(newName));
    }

    @Test
    public void deletePet() {

        int petId = 4;

        Response response = given().
                pathParam("petId", petId).
                when()
                .delete(PetEndpoints.SINGLE_PET);

        assertTrue(SUCCESS.matches(response.getStatusCode()));
    }

    @Test
    public void getSinglePet() {

        int petId = 2;

        given()
                .pathParam("petId", petId).
                when()
                .get(PetEndpoints.SINGLE_PET).
                then()
                .statusCode(is(200))
                .body("id", equalTo(petId));
    }

    @Test
    public void testPetSchemaXML() {

        int petId = 5;

        given()
                .pathParam("petId", petId).
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
                when().
                get(PetEndpoints.SINGLE_PET).
                then().
                body(matchesXsdInClasspath(PET_XSD));
    }

    @Test
    public void testPetSchemaJSON() {

        int petId = 5;

        given()
                .pathParam("petId", petId).
                when().
                get(PetEndpoints.SINGLE_PET).
                then().
                body(matchesJsonSchemaInClasspath(PET_JSON_SCHEMA));
    }

    @Test
    public void convertJSONToPojo() {

        long petId = 5L;

        Response response = given().pathParam("petId", petId).
                when().
                get(PetEndpoints.SINGLE_PET);

        assertTrue(SUCCESS.matches(response.getStatusCode()));

        Pet pet = response.getBody().as(Pet.class);

        assertEquals(petId, pet.getId().longValue());

        System.out.println(pet);
    }

    @Test
    public void captureResponseTime() {

        int petId = 3;

        long responseTime = given().pathParam("petId", petId).
                when().
                get(PetEndpoints.SINGLE_PET).time();
        System.out.println("Response time in MS: " + responseTime);
    }

    @Test
    public void assertOnResponseTime() {

        int petId = 3;

        long timeout = 1000L;

        given().pathParam("petId", petId).
                when().
                get(PetEndpoints.SINGLE_PET)
                .then()
                .time(lessThan(timeout));
    }

    @Test
    public void findPetsByTag() {

        List<String> tags = new ArrayList<>();
        tags.add("string");

        Response response = given().queryParam("tags", tags).
                when()
                .get(PetEndpoints.FIND_BY_TAGS);

        assertTrue(SUCCESS.matches(response.getStatusCode()));

        Pet[] pets = response.as(Pet[].class);

        List<Tag> responseTags = pets[0].getTags();

        boolean flag = false;

        for (Tag tag : responseTags) {
            if (tag.getName().equals(tags.get(0))) {
                flag = true;
                break;
            }
        }

        assertTrue(flag);
    }

    @Test
    public void updatePetById() {

        int petId = 3;
        String petName = "Jame";
        String petStatus = Status.AVAILABLE.toString().toLowerCase();

        Response response = given().pathParam("petId", petId).queryParam("name", petName).queryParam("status", petStatus).
                when().
                post(PetEndpoints.SINGLE_PET);

        Pet responsePet = response.as(Pet.class);

        assertTrue(SUCCESS.matches(response.statusCode()));

        assertEquals(petId, responsePet.getId().longValue());
        assertEquals(petName, responsePet.getName());
        assertEquals(petStatus, responsePet.getStatus());
    }
}
