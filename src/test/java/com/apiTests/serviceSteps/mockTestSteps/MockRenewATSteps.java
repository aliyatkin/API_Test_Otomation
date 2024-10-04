package com.apiTests.serviceSteps.mockTestSteps;

import com.apiTests.mockService.RenewAccessTokenMockService;
import com.apiTests.models.user_controller.renewAccessToken.RenewAccessTokenResponse;
import com.apiTests.requests.mockRequests.user_controller.RenewAccessTokenMockTest;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Data_Path.*;
import static com.apiTests.constants.StatusCode.NOT_OK;
import static com.apiTests.constants.StatusCode.OK;

public class MockRenewATSteps {

    private RenewAccessTokenMockTest renewAccessTokenMockTest;
    private RenewAccessTokenResponse response;
    private static RenewAccessTokenMockService renewAccessTokenMockService;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(MockRenewATSteps.class);

    // Mock renew access token with saved access token
    @And("the user refresh the access token with the saved access token for mock service")
    public void valid_mock(){

        // Initialize and start the mock service for renew access token
        renewAccessTokenMockService = new RenewAccessTokenMockService();
        renewAccessTokenMockService.startMockServer();

        // Set up the mock response for the renew access token with saved access token
        renewAccessTokenMockService.setupRenewAccessTokenMock(OK, ACCESS_TOKEN_JSON, MOCK_RENEW_RESPONSE_BODY);

        // Send a renew access token request using mock data
        renewAccessTokenMockTest = new RenewAccessTokenMockTest();
        response = renewAccessTokenMockTest.RenewAccessTokenForMock(OK,ACCESS_TOKEN_JSON);

        logger.info("The access token refreshed");

        // Stop the mock server after test
        renewAccessTokenMockService.stopMockServer();
    }

    // Mock renew access token with empty access token
    @And("the user can not refresh the access token with empty parameter for mock service")
    public void emptyParameter_mock(){

        // Initialize and start the mock service for renew access token
        renewAccessTokenMockService = new RenewAccessTokenMockService();
        renewAccessTokenMockService.startMockServer();

        // Set up the mock response for the renew access token with empty access token
        renewAccessTokenMockService.setupRenewAccessTokenMock(NOT_OK, EMPTY_JSON, MOCK_RENEW_RESPONSE_BODY);

        // Send a renew access token request using mock data
        renewAccessTokenMockTest = new RenewAccessTokenMockTest();
        response = renewAccessTokenMockTest.RenewAccessTokenForMock(NOT_OK, EMPTY_JSON);

        logger.info("The access token can not refreshed");

        // Stop the mock server after test
        renewAccessTokenMockService.stopMockServer();
    }

    // Mock renew access token with wrong access token
    @And("the user can not refresh the access token with wrong parameter, mock service")
    public void wrongParameter_mock(){

        // Initialize and start the mock service for renew access token
        renewAccessTokenMockService = new RenewAccessTokenMockService();
        renewAccessTokenMockService.startMockServer();

        // Set up the mock response for the renew access token with wrong access token
        renewAccessTokenMockService.setupRenewAccessTokenMock(NOT_OK, WRONG_ACCESS_TOKEN, MOCK_RENEW_RESPONSE_BODY);

        // Send a renew access token request using mock data
        renewAccessTokenMockTest = new RenewAccessTokenMockTest();
        response = renewAccessTokenMockTest.RenewAccessTokenForMock(NOT_OK, WRONG_ACCESS_TOKEN);

        logger.info("The access token can not refreshed");

        // Stop the mock server after test
        renewAccessTokenMockService.stopMockServer();
    }
}