package com.apiTests.requests.serviceRequests.detection_controller;

import com.apiTests.models.detection_controller.detectionsClassificationTypes.DetectionsClassificationTypesResponse;
import com.apiTests.requests.serviceRequests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.logging.log4j.*;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.apiTests.constants.ContentType.json;
import static com.apiTests.constants.Endpoint.CLASSIFICATION_ENDPOINT;
import static com.apiTests.constants.Language.*;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static io.restassured.RestAssured.given;

public class ClassificationTypesTests extends BaseTest {

    public static int responseClassificationTypeId;
    private static int listElement;
    private static String classificationTypeId;
    private static List<DetectionsClassificationTypesResponse> classificationTypeList;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(ClassificationTypesTests.class);

    /**
     * Send a login request and returns the response.
     *
     * @param statusCode           The expected status code of the response.
     * @param accessTokenPath      Path to the accessToken that is uses in header.
     * @param classificationType   Classification type id for detection request
     */
    @Step("ClassificationType parameter for Get Detections")
    public String ClassificationType(int statusCode, String accessTokenPath, String classificationType) {

        // Load the access token from the specified file path
        String accessToken = requestBodyLoader(accessTokenPath);

        Response response = given(spec)
                .when().header("Authorization", "Bearer " + accessToken)
                .header(language, tr)
                .get(CLASSIFICATION_ENDPOINT);

        // Validate the status code of the response
        response.then().statusCode(statusCode);
        // Retrieve the content type of the response
        String contentType = response.getContentType();

        // Log the response details
        logger.info("Response received: {}", response.asString());
        logger.info("Status Code: {}", response.getStatusCode());

        if (contentType != null && contentType.contains(json)) {

            // Deserialize the JSON response into a list of DetectionsClassificationTypesResponse objects
            // This uses a generic TypeRef to specify that the response should be mapped as a List of DetectionsClassificationTypesResponse
            classificationTypeList = response.as(new TypeRef<>() {
            });

            // Check if classificationTypeList is not empty before proceeding
            if (!classificationTypeList.isEmpty()) {

                ClassificationType(classificationType);

            } else {

                logger.error("The classification type list is empty!!!");
            }

        } else {

            // Log an error if the content type is unexpected and return null
            logger.error("Unexpected content type: {}", contentType);
        }
        return classificationTypeId;
    }

    public void ClassificationType(String classificationType) {

        boolean isMatchFound = false;

        // Iterate through each item in the classificationTypeList
        for (listElement = 0; listElement < classificationTypeList.size(); listElement++) {

            // Get the current DetectionsClassificationTypesResponse object from the list
            DetectionsClassificationTypesResponse classification = classificationTypeList.get(listElement);
            logger.info("{}. Classification Type: {}", listElement, classification.getName());

            // Check if the classification's name matches the provided classificationType
            if (classificationType.equals(classification.getName())) {

                // If there is a match, get the classification ID and convert it to a String
                responseClassificationTypeId = classification.getId();
                classificationTypeId = String.valueOf(responseClassificationTypeId);
                logger.info("Classification type found: {}", classificationType);
                isMatchFound = true;
                break;

            }
        }
        // Check the match the end of the for
        if (!isMatchFound) {
            logger.error("No matching classification type found for: {}", classificationType);
            Assertions.fail("No matching classification type found for: " + classificationType);
        }
    }
}
