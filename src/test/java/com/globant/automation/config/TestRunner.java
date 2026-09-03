package com.globant.automation.config;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestRunner {

    @BeforeSuite
    public void configureRestAssured() throws IOException {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("No se encontró config.properties");
            }
            properties.load(input);
        }

        RestAssured.baseURI = properties.getProperty("base.uri");
    }
}
