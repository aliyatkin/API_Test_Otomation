package com.apiTests.requests.mockRequests.user_controller;

import com.apiTests.models.user_controller.renewAccessToken.RenewAccessTokenResponse;
import com.apiTests.requests.mockRequests.MockBaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Endpoint.RENEW_ACCESS_TOKEN_ENDPOINT;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static io.restassured.RestAssured.given;

public class RenewAccessTokenMockTest extends MockBaseTest {

    // Logger instance is initialized using Log4j for logging in this class
    private static final Logger logger = LogManager.getLogger(RenewAccessTokenMockTest.class);

    /**
     * Simulates a login request and returns the response.
     *
     * @param statusCode        The expected status code of the response.
     * @param accessTokenPath   Path to the accessToken that is uses in header.
     */
    @Step("Renew Access Token")
    public RenewAccessTokenResponse RenewAccessTokenForMock(int statusCode, String accessTokenPath ) {

        // Load the access token from the specified file path
        String accessToken = requestBodyLoader(accessTokenPath);

        // Send the renew access token request to the specified endpoint with headers
        Response response = given(spec)
                .header("Authorization", "Bearer" + accessToken)
                .post(RENEW_ACCESS_TOKEN_ENDPOINT);

        // Validate the status code of the response
        response.then().statusCode(statusCode);
        // Retrieve the content type of the response
        String contentType = response.getContentType();

        // Log the response details
        logger.info("Response received: {}", response.asString());
        logger.info("Status Code: {}", response.getStatusCode());

        // Check if the content type is JSON and return the deserialized response
        if (contentType != null && contentType.contains("application/json"))  {
            return response.as(RenewAccessTokenResponse.class);
        } else {
            // Log an error if the content type is unexpected and return null
            logger.error("Unexpected content type: {}", contentType);
            return null;
        }
    }
}