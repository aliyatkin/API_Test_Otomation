package com.apiTests.requests.serviceRequests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

public class BaseTest {

    protected static RequestSpecification spec;

    static {
        RestAssured.filters(new AllureRestAssured());
    }

    public BaseTest() {
        // Dynamic base URI assignment
        String baseUri = System.getProperty("baseUri", "http://10.10.10.64:9095"); // baseUri bir class'tan çekilecek

        // Logging option, can be disabled if desired
        boolean enableLogging = Boolean.parseBoolean(System.getProperty("enableLogging", "false"));

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(baseUri);

        if (enableLogging) {
            builder.addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()));
        }

        spec = builder.build();
        // RequestSpecification setted
    }

}
