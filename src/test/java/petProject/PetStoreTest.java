package petProject;

import io.restassured.response.Response;
import petProject.config.PetConfig;
import petProject.config.PetEndpoints;
import org.junit.Test;
import petProject.pojo.Category;
import petProject.pojo.Pet;
import petProject.pojo.Tag;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetStoreTest extends PetConfig {

    @Test
    public void createNewPetByJson() {
        String petBodyJson = "{\n" +
                "  \"id\": 10,\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Dogs\"\n" +
                "  },\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
                .body(petBodyJson).
                when()
                .post(PetEndpoints.DEFAULT_PET_PATH).
                then();
    }

    @Test
    public void createNewPetByXml() {
        String petXml =
                "<pet>\n" +
                        "\t<id>10</id>\n" +
                        "\t<name>doggie</name>\n" +
                        "\t<category>\n" +
                        "\t\t<id>1</id>\n" +
                        "\t\t<name>Dogs</name>\n" +
                        "\t</category>\n" +
                        "\t<photoUrls>\n" +
                        "\t\t<photoUrl>string</photoUrl>\n" +
                        "\t</photoUrls>\n" +
                        "\t<tags>\n" +
                        "\t\t<tag>\n" +
                        "\t\t\t<id>0</id>\n" +
                        "\t\t\t<name>string</name>\n" +
                        "\t\t</tag>\n" +
                        "\t</tags>\n" +
                        "\t<status>available</status>\n" +
                        "</pet>";


        given()
                .body(petXml)
                .header("Accept", "application/xml")
                .header("Content-Type", "application/xml").
                when()
                .post(PetEndpoints.DEFAULT_PET_PATH).
                then();
    }

    @Test
    public void updatePetByJson() {
        String petBodyJson = "{\n" +
                "  \"id\": 10,\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Dogs\"\n" +
                "  },\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
                .body(petBodyJson).
                when()
                .post(PetEndpoints.DEFAULT_PET_PATH).
                then();
    }

    @Test
    public void deletePet() {
        given().
                pathParam("petId", 4).
                when()
                .delete(PetEndpoints.SINGLE_PET).
                then();
    }

    @Test
    public void getSinglePet() {
        given()
                .pathParam("petId", 2).
                when()
                .get(PetEndpoints.SINGLE_PET).
                then();
    }

    @Test
    public void testPetSerializationByJSON() {
        List<String> photoUrls = new ArrayList<String>();
        photoUrls.add("strings");

        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag(0L, "string"));

        Pet pet = new Pet(new Category(1L, "cat"), 13L, "doggie", photoUrls, "available", tags);

        given().
                body(pet).
                when().
                post(PetEndpoints.DEFAULT_PET_PATH).
                then();
    }

    @Test
    public void testPetSchemaXML() {
        given()
                .pathParam("petId", 5).
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
                when().
                get(PetEndpoints.SINGLE_PET).
                then().
                body(matchesXsdInClasspath("PetXSD.xsd"));
    }

    @Test
    public void testPetSchemaJSON() {
        given()
                .pathParam("petId", 5).
                when().
                get(PetEndpoints.SINGLE_PET).
                then().
                body(matchesJsonSchemaInClasspath("PetJsonSchema.json"));
    }

    @Test
    public void convertJSONToPojo() {
        Response response = given().pathParam("petId", 5).
                when().
                get(PetEndpoints.SINGLE_PET);

        Pet pet = response.getBody().as(Pet.class);

        System.out.println(pet.toString() );
    }

}
