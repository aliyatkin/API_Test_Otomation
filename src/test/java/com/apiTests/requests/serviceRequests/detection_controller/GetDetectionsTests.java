package com.apiTests.requests.serviceRequests.detection_controller;

import com.apiTests.models.detection_controller.getDetections.GetDetectionsResponse;
import com.apiTests.requests.serviceRequests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.apiTests.constants.ContentType.*;
import static com.apiTests.constants.Endpoint.DETECTIONS_ENDPOINT;
import static com.apiTests.constants.Language.*;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static io.restassured.RestAssured.given;
import static java.lang.Integer.parseInt;

public class GetDetectionsTests extends BaseTest {

    // Logger instance is initialized using Log4j for logging in this class
    private static final Logger logger = LogManager.getLogger(GetDetectionsTests.class);

    @Step("Get Detections")
    public void GetDetections(int statusCode, String accessTokenPath, String page, String pageSize, boolean torf){

        // Load the access token from the specified file path
        String accessToken = requestBodyLoader(accessTokenPath);

        int pageSize1 = parseInt(pageSize);

        logger.info("Access Token: {}", accessToken);
        logger.info("Page: {}, PageSize: {}", page, pageSize);

        Response response = given(spec)
                .when().header("Authorization", "Bearer " + accessToken)
                .header(language, en)
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("createdByMe", torf)
                .get(DETECTIONS_ENDPOINT);

        // Validate the status code of the response
        response.then().statusCode(statusCode);
        // Retrieve the content type of the response
        String contentType = response.getContentType();

        // Log the response details
        logger.info("Response received: {}", response.asString());
        logger.info("Status Code: {}", response.getStatusCode());

        int index;
        if (contentType != null && contentType.contains(json)) {

            List<GetDetectionsResponse> detectionsList = response.as(new TypeRef<>() {});

            for(index = 0; index < pageSize1; index++)

            {
                if (!detectionsList.isEmpty()) {

                    GetDetectionsResponse detections = detectionsList.get(index);
                    String id = detections.getId();
                    System.out.println(id);
                    logger.info("{}. Detection: {}",index, detections);

                } else {
                    Assertions.fail("Detection List is empty, please create a detection!");
                }
            }
        }
    }
}