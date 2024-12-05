package com.apiTests.requests.serviceRequests.user_controller;

import com.apiTests.models.user_controller.login.LoginResponse;
import com.apiTests.requests.serviceRequests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.ContentType.json;
import static com.apiTests.constants.Endpoint.LOGIN_ENDPOINT;
import static com.apiTests.constants.Language.*;
import static com.apiTests.requests.HelperMethod.createJson;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LoginTests extends BaseTest {

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(LoginTests.class);

    /**
     * Send a login request and returns the response.
     *
     * @param statusCode The expected status code of the response.
     * @param username   username
     * @param password   password
     * @param torf       A boolean query parameter ("True or False").
     * @return LoginResponse    If the response is in JSON format, it is deserialized into LoginResponse object.
     * Otherwise, returns null.
     */
    @Step("the user tries to logs in with provided credentials")
    public LoginResponse login(int statusCode, String username, String password, boolean torf) {

        // Create request body by using username and password
        String requestBody = createJson(username, password);

        // Send the login request to the specified endpoint with headers, query parameters, and request body
        Response response = given(spec)
                .when().header(language, en)
                .queryParam("basic", torf)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(LOGIN_ENDPOINT);

        // Validate the status code of the response
        response.then().statusCode(statusCode);
        // Retrieve the content type of the response
        String contentType = response.getContentType();

        // Log the response details
        logger.info("Response received: {}", response.asString());
        logger.info("Status Code: {}", response.getStatusCode());

        // Check if the content type is JSON and return the response
        if (contentType != null && contentType.contains(json)) {
            response.then().assertThat().body(matchesJsonSchemaInClasspath(
                    "loginResponseSchema.json"));
            return response.as(LoginResponse.class);
        } else {
            // Log an error if the content type is unexpected and return null
            logger.error("Unexpected content type: {}", contentType);
            return null;
        }
    }
}