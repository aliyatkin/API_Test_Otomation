package com.apiTests.requests.serviceRequests.zone_controller;

import com.apiTests.models.zone_controller.ZonesResponse;
import com.apiTests.requests.serviceRequests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.apiTests.constants.ContentType.json;
import static com.apiTests.constants.Endpoint.ZONES_ENDPOINT;
import static com.apiTests.constants.Language.language;
import static com.apiTests.constants.Language.tr;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static io.restassured.RestAssured.given;

public class ZonesTests extends BaseTest {

    public static int responseZoneId;
    private static int listElement;
    private static String zoneId;
    private static List<ZonesResponse> zonesList;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(ZonesTests.class);

    @Step("Zones")
    public String Zone(int statusCode, String accessTokenPath, String zone) {

        // Load the access token from the specified file path
        String accessToken = requestBodyLoader(accessTokenPath);

        Response response = given(spec)
                .when().header("Authorization", "Bearer " + accessToken)
                .header(language, tr)
                .get(ZONES_ENDPOINT);

        // Validate the status code of the response
        response.then().statusCode(statusCode);
        // Retrieve the content type of the response
        String contentType = response.getContentType();

        // Log the response details
        logger.info("Response received: {}", response.asString());
        logger.info("Status Code: {}", response.getStatusCode());

        if (contentType != null && contentType.contains(json)) {

            // Deserialize the JSON response into a list of ZonesResponse objects
            // This uses a generic TypeRef to specify that the response should be mapped as a List of ZonesResponse
            zonesList = response.as(new TypeRef<>() {
            });

            // Check if zonesList is not empty before proceeding
            if (!zonesList.isEmpty()) {

                Zones(zone);

            } else {

                logger.error("The zones list is empty!!!");
            }
        } else {

            // Log an error if the content type is unexpected and return null
            logger.error("Unexpected content type: {}", contentType);
        }

        return zoneId;
    }

    public void Zones(String zone) {

        // Iterate through each item in the zonesList
        for (listElement = 0; listElement < zonesList.size(); listElement++) {

            // Get the current ZonesResponse object from the list
            ZonesResponse zones = zonesList.get(listElement);
            logger.info("{}. Zone: {}", listElement, zones.getName());

            // Check if the zone's name matches the provided zone name
            if (zone.equals(zones.getName())) {

                // If there is a match, get the zone ID and convert it to a String
                responseZoneId = zones.getId();
                zoneId = String.valueOf(responseZoneId);
                break;

            } else {
                logger.error("No matching zone found for this index: {}.index", listElement);
            }
        }
        if(zoneId != null){
            logger.info("Zone Id found");
        }else {
            Assertions.fail("No matching zone found");
        }
    }
}
