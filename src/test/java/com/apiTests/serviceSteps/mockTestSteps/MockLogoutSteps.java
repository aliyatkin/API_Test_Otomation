package com.apiTests.serviceSteps.mockTestSteps;

import com.apiTests.mockService.LogoutMockService;
import com.apiTests.requests.mockRequests.user_controller.LogoutMockTest;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Data_Path.*;
import static com.apiTests.constants.StatusCode.UNAUTHORIZED;

public class MockLogoutSteps {

    private LogoutMockTest logoutMockTest;
    private Response response;
    private static LogoutMockService logoutMockService;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(MockLogoutSteps.class);

    // Mock logout with saved access token
    @And("the user logs out from the system with the saved access token for mock service")
    public void logout_mock(){

        // Initialize and start the mock service for logout
        logoutMockService = new LogoutMockService();
        logoutMockService.startMockServer();

        // Set up the mock response for the logout with saved access token
        logoutMockService.setupLogoutMock(UNAUTHORIZED, ACCESS_TOKEN_JSON, MOCK_LOGOUT_RESPONSE_BODY );

        // Send a logout request using mock data
        logoutMockTest = new LogoutMockTest();
        response = logoutMockTest.LogoutForMock(UNAUTHORIZED, ACCESS_TOKEN_JSON);

        // Stop the mock server after test
        logoutMockService.stopMockServer();
    }
}