package com.apiTests.requests.mockRequests;

import com.apiTests.models.user_controller.renewAccessToken.RenewAccessTokenResponse;
import com.apiTests.requests.serviceRequests.user_controller.RenewAccessTokenTests;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Data_Path.accessTokenJSONPath;
import static com.apiTests.constants.Endpoint.RENEW_ACCESS_TOKEN_ENDPOINT;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static com.apiTests.requests.mockRequests.MockBaseTest.spec;
import static io.restassured.RestAssured.given;

public class RenewAccessTokenMockTest {

    private static final Logger logger = LogManager.getLogger(RenewAccessTokenTests.class);

    @Step("Renew Access Token")
    public RenewAccessTokenResponse RenewAccessTokenForMock(int statusCode, String accessTokenPath ) {

        String accessToken = requestBodyLoader(accessTokenPath);

        Response response = given(spec)
                .header("Authorization", "Bearer" + accessToken)  // Access token'i header'a ekle
                .accept("*/*")  // Accept header'ını ekle
                .post(RENEW_ACCESS_TOKEN_ENDPOINT);  // RENEW_ACCESS_TOKEN'a POST isteği gönder

        // Durum kodunu doğrula
        response.then().statusCode(statusCode);

        String contentType = response.getContentType();
        logger.info("Response received: " + response.asString());
        logger.info("Status Code: " + response.getStatusCode());

        if (contentType != null && contentType.contains("application/json"))  {
            return response.as(RenewAccessTokenResponse.class);
        } else {
            // JSON değilse raw response'u logla
            logger.error("Unexpected content type: " + contentType);
            logger.error("Response body: " + response.asString());
            return null;
        }
    }
}
