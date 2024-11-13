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

public class GetDetectionsTests extends BaseTest {

    public static int intClassificationTypeId;
    public static int responseClassificationTypeId;
    public static int listElement;
    private static List<GetDetectionsResponse> detectionsList;  // Define detectionsList here for accessibility in both methods

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(GetDetectionsTests.class);

    /**
     * Send a login request and returns the response.
     *
     * @param statusCode           The expected status code of the response.
     * @param accessTokenPath      Path to the accessToken that is uses in header.
     * @param page                 Page parameter for detections request
     * @param pageSize             PageSize parameter for detections request
     * @param torf                 A boolean query parameter ("True or False").
     * @param classificationTypeId Classification type id for detection request
     */
    @Step("Get Detections")
    public void GetDetections(int statusCode, String accessTokenPath, String page, String pageSize, boolean torf, String classificationTypeId) {

        // If the classificationTypeId value is 0, convert it to null to prevent it from affecting filtering
        if (classificationTypeId == ("0")) {
            classificationTypeId = null;
        }

        // Load the access token from the specified file path
        String accessToken = requestBodyLoader(accessTokenPath);

        Response response = given(spec)
                .when().header("Authorization", "Bearer " + accessToken)
                .header(language, en)
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("filterByClassificationTypeId", classificationTypeId)
                .queryParam("createdByMe", torf)
                .get(DETECTIONS_ENDPOINT);

        // Validate the status code of the response
        response.then().statusCode(statusCode);
        // Retrieve the content type of the response
        String contentType = response.getContentType();

        // Log the response details
        logger.info("Response received: {}", response.asString());
        logger.info("Status Code: {}", response.getStatusCode());

        // Check if the content type is JSON and return the response
        if (contentType != null && contentType.contains(json)) {

            // The response is saved in a list because it is an array
            detectionsList = response.as(new TypeRef<>() {
            });

            // Check if the list is empty. If the list is not empty, continue processing
            if (!detectionsList.isEmpty()) {

                filterClassificationType(classificationTypeId);

            } else {
                // If the list is empty, log an error message
                logger.error("Detection List is empty!");
            }

        } else {
            // Log an error if the content type is unexpected and return null
            logger.error("Unexpected content type: {}", contentType);
        }
    }

    public void filterClassificationType(String classificationTypeId) {

        // Check if the classificationTypeId is null. If it is not, continue processing
        if (classificationTypeId != null) {

            // Convert the parameter from a string to an integer to use it in the if statement comparison
            intClassificationTypeId = Integer.parseInt(classificationTypeId);

            // The `for` loop will continue based on the size of the detection list
            for (listElement = 0; listElement < detectionsList.size(); listElement++) {

                // We iterate through all the elements in the list and assign them to the POJO class one by one
                GetDetectionsResponse detections = detectionsList.get(listElement);
                logger.info("{}. Detection: {} , {}", listElement, detections.getId(), detections.getType().getName());

                // Get the classification type ID from the response and save it as an integer
                responseClassificationTypeId = detections.getType().getId();

                // This code checks if intClassificationTypeId and responseClassificationTypeId are equal
                // If they are, it logs an info message confirming that the IDs match
                Assertions.assertEquals(intClassificationTypeId, responseClassificationTypeId, "The ID of the elements in the response does not match the filtered ID in the request.");
            }
        }else{
            logger.error("The classificationTypeId is null !");
        }
    }
}
