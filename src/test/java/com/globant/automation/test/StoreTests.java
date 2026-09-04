package com.globant.automation.test;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.Order;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class StoreTests extends TestRunner {

    @Test(testName = "Crear Orden de Compra")
    public void testCreateStoreOrder() {
        long orderId = System.currentTimeMillis() % 10000;
        Order newOrder = new Order(orderId, 201, 2, "placed", true);

        RestAssured
                .given()
                .baseUri(getBaseUrl())
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(newOrder)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .body("id", equalTo((int) orderId))
                .body("status", equalTo("placed"));
    }
}
