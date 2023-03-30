package petStoreProject.store;

import io.restassured.response.Response;
import org.junit.Test;
import petStoreProject.store.config.StoreEndpoints;
import petStoreProject.store.config.StoreErrorConfig;
import petStoreProject.store.pojo.Order;

import static io.restassured.RestAssured.given;
import static io.restassured.internal.http.Status.FAILURE;
import static org.junit.Assert.assertTrue;

public class StoreErrorTest extends StoreErrorConfig {

    @Test
    public void testPostOrder_failIncorrectDate() {

        int orderId = 213;

        String date = "Fail";
        Order order = new Order(198772, 7, orderId, date, true, "approved");

        Response response = given().
                body(order).
                when().
                post(StoreEndpoints.STORE_ORDER);

        assertTrue(FAILURE.matches(response.getStatusCode()));
    }

    @Test
    public void getOrderById_failUnknownId(){
        int orderId = 1010123;

        Response response = given()
                .pathParam("orderId", orderId)
                .when()
                .get(StoreEndpoints.STORE_ORDER_BY_ID);

        assertTrue(FAILURE.matches(response.getStatusCode()));
    }

    @Test
    public void deleteOrderById_failUnknownId(){
        int orderId = 1010123;

        given()
                .pathParam("orderId", orderId)
                .when()
                .delete(StoreEndpoints.STORE_ORDER_BY_ID);
    }
}
