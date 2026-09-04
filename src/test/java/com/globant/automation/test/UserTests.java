package com.globant.automation.test;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTests extends TestRunner {

    @Test(testName = "Crear Usuario")
    public void testCreateUser() {
        User newUser = new User(1001, "user_perfdog_1001", "pass1234");

        RestAssured
                .given()
                .baseUri(getBaseUrl())
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(newUser)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

    @Test(testName = "Login Usuario")
    public void testLoginUser() {
        String username = "user_login_" + System.currentTimeMillis();
        User user = new User(System.currentTimeMillis() % 10000, username, "pass123");

        given().baseUri(getBaseUrl()).contentType(ContentType.JSON).body(user).post("/user");

        RestAssured
                .given()
                .baseUri(getBaseUrl())
                .queryParam("username", username)
                .queryParam("password", "pass123")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .get("/user/login")
                .then()
                .statusCode(200)
                .body("message", containsString("logged in user session"));
    }

    @Test(testName = "Logout Usuario")
    public void testLogoutUser() {
        RestAssured
                .given()
                .baseUri(getBaseUrl())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .get("/user/logout")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }
}
