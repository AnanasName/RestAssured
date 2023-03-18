import config.PetConfig;
import config.PetEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
    public void updatePetByJson(){
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
    public void deletePet(){
        given().
        when()
                .delete("pet/1").
        then();
    }
}
