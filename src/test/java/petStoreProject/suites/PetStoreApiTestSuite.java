package petStoreProject.suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import petStoreProject.pet.GPathXMLTests;
import petStoreProject.pet.PetErrorTest;
import petStoreProject.pet.PetTest;


@Suite
@SelectClasses({PetTest.class, PetErrorTest.class, GPathXMLTests.class})
@SuiteDisplayName("Pet Store Api Tests")
public class PetStoreApiTestSuite {
}
