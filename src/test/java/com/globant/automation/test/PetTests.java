package com.globant.automation.test;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.Pet;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetTests extends TestRunner {

    @Test(testName = "Listar Mascotas Disponibles")
    public void testListAvailablePets() {
        RestAssured
                .given()
                .baseUri(getBaseUrl())
                .queryParam("status", "available")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("status", everyItem(equalTo("available")));
    }

    @Test(testName = "Consultar Mascota Específica")
    public void testGetSpecificPet() {
        long petId = System.currentTimeMillis() % 1000000;
        Pet newPet = new Pet(petId, "FirulaisPerfDog", "available");

        given().baseUri(getBaseUrl()).contentType(ContentType.JSON).body(newPet).post("/pet");

        RestAssured
                .given()
                .baseUri(getBaseUrl())
                .pathParam("petId", petId)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("id", equalTo((int) petId))
                .body("name", equalTo("FirulaisPerfDog"));
    }
}
