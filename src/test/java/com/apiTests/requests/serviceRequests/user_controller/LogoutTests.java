package com.apiTests.requests.serviceRequests.user_controller;

import com.apiTests.requests.serviceRequests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Endpoint.LOGOUT_ENDPOINT;
import static io.restassured.RestAssured.given;

public class LogoutTests extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LogoutTests.class);

    @Step("User logs out with provided access token")
    public void Logout(String accessToken, int statusCode) {

        // Send request
        Response response = given(spec)
                .when()
                .post(LOGOUT_ENDPOINT);

        response.then().statusCode(statusCode);

        logger.info("Status Code: " + response.getStatusCode());
    }
}
