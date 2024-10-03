package com.apiTests.requests.mockRequests.user_controller;

import com.apiTests.requests.mockRequests.MockBaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Endpoint.LOGOUT_ENDPOINT;
import static io.restassured.RestAssured.given;


public class LogoutMockTest extends MockBaseTest {

    // Initialize a logger using Log4j for the LoginMockTest class
    private static final Logger logger = LogManager.getLogger(LogoutMockTest.class);

    @Step("User logs in with provided credentials")
    public Response LogoutForMock(String accessToken, int statusCode){

        // Send request to logout endpoint
        Response response = given(spec)
                .header("Authorization", "Bearer " + accessToken)
                .post(LOGOUT_ENDPOINT);

        response.then().statusCode(statusCode);          // Check the status code: As expected?
        String contentType = response.getContentType();  // Store the content type of the response in a String

        logger.info("Response received: {}", response.asString());
        logger.info("Status Code: {}", response.getStatusCode());

        // Return response
        if (contentType != null && contentType.contains("text/plain;charset=UTF-8"))  {
            return response;
        } else {
            // If not JSON, log the raw response and return null
            logger.error("Unexpected content type: {}", contentType);
            return null;
        }
    }
}
