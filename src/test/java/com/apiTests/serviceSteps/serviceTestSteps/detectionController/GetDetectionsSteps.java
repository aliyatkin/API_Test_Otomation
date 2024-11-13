package com.apiTests.serviceSteps.serviceTestSteps.detectionController;

import com.apiTests.requests.serviceRequests.detection_controller.*;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.DataPath.*;
import static com.apiTests.constants.StatusCode.*;

public class GetDetectionsSteps {

    private GetDetectionsTests getDetectionsTests;
    private ClassificationTypesTests classificationTypesTests;
    private String classificationTypeId;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(GetDetectionsSteps.class);

    @And("the user need to enter {string}")
    public void setGetDetectionClassificationType(String classificationType){

        classificationTypesTests = new ClassificationTypesTests();

        classificationTypeId = classificationTypesTests.ClassificationType(OK, ACCESS_TOKEN, classificationType);

    }

    @When("the user gets Detections for {string} and {string}")
    public void getDetections(String page, String pageSize) {

        getDetectionsTests = new GetDetectionsTests();

        getDetectionsTests.GetDetections(OK, ACCESS_TOKEN, page, pageSize, true, classificationTypeId);
    }
}
