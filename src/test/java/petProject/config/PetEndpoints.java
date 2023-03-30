package petProject.config;

public interface PetEndpoints {

    String DEFAULT_PET_PATH = "pet";
    String SINGLE_PET = "pet/{petId}";

    String FIND_BY_STATUS = "pet/findByStatus";
    String FIND_BY_TAGS = "pet/findByTags";
}
