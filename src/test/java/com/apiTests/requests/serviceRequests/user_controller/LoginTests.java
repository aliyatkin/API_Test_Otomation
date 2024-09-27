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

    private static final Logger logger = LogManager.getLogger(LoginTests.class);

    @Step("User logs in with provided credentials")
    public LoginResponse Login(String requestBodyPath, int statusCode, boolean torf) {

        // Read the data from JSON file and save it in requestBody
        String requestBody = requestBodyLoader(requestBodyPath);
        logger.info("Request body loaded from: " + requestBodyPath);

        // Send request
        Response response = given(spec)
                .when().header("Accept-Language", "en")
                .queryParam("basic", torf)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(LOGIN_ENDPOINT); // endpoint'ler bir class'tan çekilecek

        response.then().statusCode(statusCode);
        String contentType = response.getContentType();

        logger.info("Response received: " + response.asString());
        logger.info("Status Code: " + response.getStatusCode());

        // Return response
        if (contentType != null && contentType.contains("application/json"))  {
            return response.as(LoginResponse.class);
        } else {
            // If not JSON, log the raw response
            logger.error("Unexpected content type: " + contentType);
            logger.error("Response body: " + response.asString());
            return null;
        }
    }
}
