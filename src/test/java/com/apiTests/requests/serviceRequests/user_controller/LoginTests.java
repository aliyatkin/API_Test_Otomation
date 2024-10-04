package com.apiTests.requests.serviceRequests.user_controller;

import com.apiTests.models.user_controller.login.LoginResponse;
import com.apiTests.requests.serviceRequests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Endpoint.LOGIN_ENDPOINT;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static io.restassured.RestAssured.given;

public class LoginTests extends BaseTest {

    // Logger instance is initialized using Log4j for logging in this class
    private static final Logger logger = LogManager.getLogger(LoginTests.class);

    /**
     * Simulates a login request and returns the response.
     *
     * @param statusCode        The expected status code of the response.
     * @param requestBodyPath   Path to the request body JSON file.
     * @param torf              A boolean query parameter ("True or False").
     * @return LoginResponse    If the response is in JSON format, it is deserialized into LoginResponse object.
     *                          Otherwise, returns null.
     */
    @Step("User logs in with provided credentials")
    public LoginResponse Login(int statusCode, String requestBodyPath, boolean torf) {

        // Load the request body from the specified file path
        String requestBody = requestBodyLoader(requestBodyPath);

        // Log the loaded request body file path
        logger.info("Request body loaded from: {}", requestBodyPath);

        // Send the login request to the specified endpoint with headers, query parameters, and request body
        Response response = given(spec)
                .when().header("Accept-Language", "en")
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

        // Check if the content type is JSON and return the deserialized response
        if (contentType != null && contentType.contains("application/json"))  {
            return response.as(LoginResponse.class);
        } else {
            // Log an error if the content type is unexpected and return null
            logger.error("Unexpected content type: {}", contentType);
            return null;
        }
    }
}