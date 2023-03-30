package petStoreProject.store;

import org.junit.Test;
import petStoreProject.store.config.StoreConfig;
import petStoreProject.store.config.StoreEndpoints;
import petStoreProject.store.pojo.Order;

import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoreTest extends StoreConfig {
    @Test
    public void testPostOrder(){

        int orderId = 213;

        String date = OffsetDateTime
                .now( ZoneOffset.UTC )
                .minus( Period.ofYears( 1 ) )
                .toString();
        Order order = new Order(198772, 7, orderId, date, true, "approved");

        given().
                body(order).
                when().
                post(StoreEndpoints.STORE_ORDER)
                .then()
                .body("id", equalTo(orderId));
    }
}
