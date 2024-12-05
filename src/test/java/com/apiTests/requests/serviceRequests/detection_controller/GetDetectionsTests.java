package com.apiTests.requests.serviceRequests.detection_controller;

import com.apiTests.models.detection_controller.getDetections.GetDetectionsResponse;
import com.apiTests.models.user_controller.login.LoginResponse;
import com.apiTests.requests.serviceRequests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.apiTests.constants.ContentType.*;
import static com.apiTests.constants.Endpoint.DETECTIONS_ENDPOINT;
import static com.apiTests.constants.Language.*;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static io.restassured.RestAssured.given;

public class GetDetectionsTests extends BaseTest {

    public static int intClassificationTypeId;
    public static int intZoneId;
    public static int responseClassificationTypeId;
    public static int responseZoneId;
    public static int listElement;
    public static String filterStartTime;
    public static String filterFinishTime;
    private static List<GetDetectionsResponse> detectionsList;  // Define detectionsList here for accessibility in both methods

    private static LoginResponse loginResponse;
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
    public void GetDetections(int statusCode, String accessTokenPath, String page, String pageSize, boolean torf, String classificationTypeId, String zoneId,
                              long startTime, long finishTime) {

        if (startTime == 0) {
            //filterStartTime = String.valueOf(startTime);
            filterStartTime = null;
        } else {
            filterStartTime = String.valueOf(startTime);
        }
        if (finishTime == 0) {
            //filterFinishTime = String.valueOf(finishTime);
            filterFinishTime = null;
        } else {
            filterFinishTime = String.valueOf(finishTime);
        }

        // Load the access token from the specified file path
        String accessToken = requestBodyLoader(accessTokenPath);

        Response response = given(spec)
                .when().header("Authorization", "Bearer " + accessToken)
                .header(language, en)
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("filterByClassificationTypeId", classificationTypeId)
                .queryParam("filterByStartTime", filterStartTime)
                .queryParam("filterByEndTime", filterFinishTime)
                .queryParam("filterByZoneId", zoneId)
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

                // Run the classification type filter
                filterClassificationType(classificationTypeId);

                //Run the zone filter
                filterZone(zoneId);

                // Run the start time and finish time filter
                filterTime(filterStartTime, filterFinishTime);

                createdByMeCheck(torf);

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
                // If they are not, it logs an error message that the IDs does not match
                Assertions.assertEquals(intClassificationTypeId, responseClassificationTypeId, "The ID of the elements in the response does not match the filtered ID in the request.");
            }
        } else {
            logger.error("The classification type filter does not used!");
        }
    }

    public void filterZone(String zoneId) {

        // Check if the zoneId is null. If it is not, continue processing
        if (zoneId != null) {

            // Convert the parameter from a string to an integer to use it in the if statement comparison
            intZoneId = Integer.parseInt(zoneId);

            // The `for` loop will continue based on the size of the detection list
            for (listElement = 0; listElement < detectionsList.size(); listElement++) {

                // We iterate through all the elements in the list and assign them to the POJO class one by one
                GetDetectionsResponse detections = detectionsList.get(listElement);
                logger.info("{}. Detection: {} , {}", listElement, detections.getId(), detections.getZone().getName());

                // Get the  zone ID from the response and save it as an integer
                responseZoneId = detections.getZone().getId();

                // This code checks if intZoneId and responseZoneId are equal
                // If they are not, it logs an error message that the IDs does not match
                Assertions.assertEquals(intZoneId, responseZoneId, "The ID of the elements in the response does not match the filtered ID in the request.");
            }
        } else {
            logger.error("The zone filter does not used!");
        }
    }

    /**
     * Converts a human-readable date string into a timestamp in milliseconds.
     *
     * @param humanDate the date string in the format "yyyy-MM-dd HH:mm:ss"
     * @return the timestamp (in milliseconds) representing the provided date,
     * or null if the date string is invalid or parsing fails.
     */
    public Long parseDateToTimestamp(String humanDate) {
        try {
            // Create a date format with the specified pattern
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Parse the string into a Date object
            Date date = dateFormat.parse(humanDate);

            // Return the timestamp (milliseconds since epoch) of the parsed date
            return date.getTime();
        } catch (Exception e) {
            // Log the error or handle it appropriately
            System.err.println("Invalid date format: " + humanDate);
            return null; // Return null if parsing fails
        }
    }

    /**
     * Filters a list of detections based on provided start and finish time filters.
     *
     * @param filterStartTime  the start time filter as a string (nullable)
     * @param filterFinishTime the finish time filter as a string (nullable)
     */
    public void filterTime(String filterStartTime, String filterFinishTime) {

        boolean time = false, start = false, finish;

        // Case 1: Both start and finish times are provided
        if (filterStartTime != null && filterFinishTime != null) {

            // Parse start and finish times from string to long
            long startTime = Long.parseLong(filterStartTime);
            long finishTime = Long.parseLong(filterFinishTime);

            // Iterate through the list of detections
            for (listElement = 0; listElement < detectionsList.size(); listElement++) {

                // Get the current detection from the list
                GetDetectionsResponse detections = detectionsList.get(listElement);

                // Check if detectionUpdateTime is null
                if (detections.getDetectionUpdateTime() == null) {

                    // Compare detectionCreateTime with start and finish times
                    if (startTime < detections.getDetectionCreateTime() && detections.getDetectionCreateTime() < finishTime) {

                        time = true;
                    }
                } else {

                    // Compare detectionUpdateTime with start and finish times
                    if (startTime < detections.getDetectionUpdateTime() && detections.getDetectionUpdateTime() < finishTime) {

                        time = true;
                    }
                }
                if (!time) {

                    Assertions.fail("The start and finish time do not work!");
                    logger.error("The start and finish time filter do not work for: {}", listElement);
                }
            }
            logger.info("The start and finish time filter work without any problem!");

            // Case 2: Only start time is provided, finish time is considered as the current time
        } else if (filterStartTime != null && filterFinishTime == null) {

            // Get the current system time in milliseconds
            long currentTime = Instant.now().toEpochMilli();

            // Parse start time from string to long
            long startTime = Long.parseLong(filterStartTime);

            // Iterate through the list of detections
            for (listElement = 0; listElement < detectionsList.size(); listElement++) {

                // Get the current detection from the list
                GetDetectionsResponse detections = detectionsList.get(listElement);

                // Check if detectionUpdateTime is null
                if (detections.getDetectionUpdateTime() == null) {

                    // Compare detectionCreateTime with start time and current time
                    if (startTime < detections.getDetectionCreateTime() && detections.getDetectionCreateTime() < currentTime) {

                        start = true;
                    }
                } else {

                    // Compare detectionUpdateTime with start time and current time
                    if (startTime < detections.getDetectionUpdateTime() && detections.getDetectionUpdateTime() < currentTime) {

                        start = true;
                    }
                }

                if(!start){

                    Assertions.fail("The start time filter does not work!");
                    logger.error("The start time filter do not work for: {}", listElement);
                }
            }

            logger.info("The start time filter work without any problem!");

            // Case 3: Start time is null and finish time is provided (this case is not allowed)
        }
        else if (filterStartTime == null && filterFinishTime != null) {
            // Log an error as this case is considered invalid
            logger.error("The start time is null but the finish time is not null: This situation is not possible!");
            Assertions.fail("The start time is null but the finish time is not null: This situation is not possible!");

            // Case 4: Both start and finish times are null
        } else {
            // Log an info message as no filtering will be applied
            logger.info("The start time and the finish time filter do not used!");
        }
    }

    public void createdByMeCheck(boolean createdByMe) {

        if (createdByMe) {

            for (listElement = 0; listElement < detectionsList.size(); listElement++) {

                GetDetectionsResponse detections = detectionsList.get(listElement);

                if (detections.getUsername() == null) {
                    //logger.info(" createdByMe = true and Username is null");
                } else {
                    Assertions.fail("Username must be null !!");
                }
            }

        } else {

            for (listElement = 0; listElement < detectionsList.size(); listElement++) {

                GetDetectionsResponse detections = detectionsList.get(listElement);

                if (detections.getUsername() != null) {

                    logger.info(" createdByMe = false and username is not null and detections created by another user");
                } else {
                    Assertions.fail("username can not be null or its own username");
                }
            }
        }
    }
}
