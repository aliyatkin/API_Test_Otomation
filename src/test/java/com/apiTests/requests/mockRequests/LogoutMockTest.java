package com.apiTests.requests.mockRequests;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Endpoint.LOGOUT_ENDPOINT;
import static io.restassured.RestAssured.given;

public class LogoutMockTest extends MockBaseTest{

    private static final Logger logger = LogManager.getLogger(LogoutMockTest.class);

    @Step("User logs in with provided credentials")
    public Response LogoutForMock(String accessToken, int statusCode){

        // Send request to logout endpoint
        Response response = given(spec)
                .header("Authorization", "Bearer " + accessToken)  // Access token'i header'a ekle
                .post(LOGOUT_ENDPOINT);  // Logout endpoint'e POST isteği gönder

        // Durum kodunu doğrula
        response.then().statusCode(statusCode);

        String contentType = response.getContentType();
        logger.info("Response received: " + response.asString());
        logger.info("Status Code: " + response.getStatusCode());

        if (contentType != null && contentType.contains("text/plain;charset=UTF-8"))  {
            return response;
        } else {
            // JSON değilse raw response'u logla
            logger.error("Unexpected content type: " + contentType);
            logger.error("Response body: " + response.asString());
            return null;
        }

    }
}
