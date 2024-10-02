package com.apiTests.requests.mockRequests.user_controller;

import com.apiTests.models.user_controller.login.LoginResponse;
import com.apiTests.requests.mockRequests.MockBaseTest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Endpoint.LOGIN_ENDPOINT;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static io.restassured.RestAssured.given;

public class LoginMockTest extends MockBaseTest {

    //
    private static final Logger logger = LogManager.getLogger(LoginMockTest.class);

    @Step("User logs in with provided credentials")
    public LoginResponse LoginForMock(String requestBodyPath, int statusCode, boolean torf) {

        // It goes to the paths given to the method and writes the String inside those paths to a String
        String requestBody = requestBodyLoader(requestBodyPath);

        logger.info("Request body loaded from: " + requestBodyPath);

        // Send request to login endpoint
        Response response = given(spec)
                .when().header("Accept-Language", "en")
                .queryParam("basic", torf)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(LOGIN_ENDPOINT);

        response.then().statusCode(statusCode);         // Check the status code: As expected?
        String contentType = response.getContentType(); // Store the content type of the response in a String

        logger.info("Response received: " + response.asString());
        logger.info("Status Code: " + response.getStatusCode());

        // Return response
        if (contentType != null && contentType.contains("application/json"))  {
            return response.as(LoginResponse.class);
        } else {
            // If not JSON, log the raw response and return null
            logger.error("Unexpected content type: " + contentType);
            return null;
        }
    }
}
