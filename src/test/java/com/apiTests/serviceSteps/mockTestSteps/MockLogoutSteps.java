package com.apiTests.serviceSteps.mockTestSteps;


import com.apiTests.mockService.LogoutMockService;
import com.apiTests.requests.mockRequests.LogoutMockTest;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Data_Path.*;
import static com.apiTests.constants.StatusCode.NOT_OK;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;

public class MockLogoutSteps {

    private LogoutMockTest logoutMockTest;
    private Response response;
    private static LogoutMockService logoutMockService;
    private static final Logger logger = LogManager.getLogger(MockLogoutSteps.class);


    @And("User logs out from the system with the saved access token, Mock Service")
    public void logoutForMock(){

        String accessToken = requestBodyLoader(accessTokenPath);

        logoutMockService = new LogoutMockService();
        logoutMockService.startMockServer();  // Mock sunucuyu başlat
        logoutMockService.setupLogoutMock(accessToken, NOT_OK, mockLogoutResponse );   // Mock yanıtını ayarla

        logoutMockTest = new LogoutMockTest();
        response = logoutMockTest.LogoutForMock(accessToken, NOT_OK);

        logoutMockService.stopMockServer();
    }
}
