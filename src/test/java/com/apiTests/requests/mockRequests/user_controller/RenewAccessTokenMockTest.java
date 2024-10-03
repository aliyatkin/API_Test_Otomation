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

    // Initialize a logger using Log4j for the LoginMockTest class
    private static final Logger logger = LogManager.getLogger(RenewAccessTokenMockTest.class);

    @Step("Renew Access Token")
    public RenewAccessTokenResponse RenewAccessTokenForMock(int statusCode, String accessTokenPath ) {

        // It goes to the paths given to the method and writes the String inside those paths to a String
        String accessToken = requestBodyLoader(accessTokenPath);

        // Send request to renew access token endpoint
        Response response = given(spec)
                .header("Authorization", "Bearer" + accessToken)
                .accept("*/*")
                .post(RENEW_ACCESS_TOKEN_ENDPOINT);

        response.then().statusCode(statusCode);         // Check the status code: As expected?
        String contentType = response.getContentType(); // Store the content type of the response in a String

        logger.info("Response received: {}", response.asString());
        logger.info("Status Code: {}", response.getStatusCode());

        // Return response
        if (contentType != null && contentType.contains("application/json"))  {
            return response.as(RenewAccessTokenResponse.class);
        } else {
            // If not JSON, log the raw response and return null
            logger.error("Unexpected content type: {}", contentType);
            return null;
        }
    }
}
