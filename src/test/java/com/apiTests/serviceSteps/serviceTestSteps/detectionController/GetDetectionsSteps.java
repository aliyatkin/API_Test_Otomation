package com.apiTests.serviceSteps.serviceTestSteps.detectionController;

import com.apiTests.requests.serviceRequests.detection_controller.*;
import com.apiTests.requests.serviceRequests.zone_controller.ZonesTests;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.DataPath.*;
import static com.apiTests.constants.StatusCode.*;

public class GetDetectionsSteps {

    private GetDetectionsTests getDetectionsTests;
    private ClassificationTypesTests classificationTypesTests;
    private ZonesTests zonesTests;
    private String classificationTypeId;
    private String zoneId;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(GetDetectionsSteps.class);

    @And("the user need to enter {string} and {string}")
    public void setGetDetectionClassificationType(String classificationType, String zone){

        classificationTypesTests = new ClassificationTypesTests();
        zonesTests = new ZonesTests();

        classificationTypeId = classificationTypesTests.ClassificationType(OK, ACCESS_TOKEN, classificationType);
        zoneId = zonesTests.Zone(OK, ACCESS_TOKEN, zone);
    }

    @When("the user gets Detections for {string} and {string}")
    public void getDetections(String page, String pageSize) {

        getDetectionsTests = new GetDetectionsTests();

        getDetectionsTests.GetDetections(OK, ACCESS_TOKEN, page, pageSize, true, classificationTypeId, zoneId);
    }
}
