package com.globant.automation.config;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestRunner {

    public static final Properties PROPERTIES = new Properties();

    private static String baseUrl;
    private static String apiKey;

    @BeforeSuite
    public void setUpEnvironment() throws IOException {
        loadProperties();
        baseUrl = getConfigVariable("base.uri");
        apiKey = getConfigVariable("apikey");
        RestAssured.baseURI = baseUrl;
    }

    private void loadProperties() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IOException("No se encontró config.properties en los recursos de prueba");
            }
            PROPERTIES.load(inputStream);
        }
    }

    public String getConfigVariable(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getApiKey() {
        return apiKey;
    }
}