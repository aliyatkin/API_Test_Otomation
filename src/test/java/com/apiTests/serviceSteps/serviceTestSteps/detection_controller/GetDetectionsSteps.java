package com.apiTests.serviceSteps.serviceTestSteps.detection_controller;

import com.apiTests.models.detection_controller.getDetections.GetDetectionsResponse;
import com.apiTests.requests.serviceRequests.detection_controller.*;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.DataPath.*;
import static com.apiTests.constants.StatusCode.*;

public class GetDetectionsSteps {

    private GetDetectionsTests getDetectionsTests;
    private GetDetectionsResponse getDetectionsResponse;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(GetDetectionsSteps.class);

    @And("the user gets Detections for {string} and {string}")
    public void getDetections(String page, String pageSize) {

        getDetectionsTests = new GetDetectionsTests();

        getDetectionsTests.GetDetections(OK, ACCESS_TOKEN, page, pageSize, true);
    }
}
