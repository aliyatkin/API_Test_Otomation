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
    private static final Logger logger = LogManager.getLogger(MockRenewATSteps.class);

    @And("User refresh the access token with the saved access token, Mock Service")
    public void renewAccessToken_mock(){

        renewAccessTokenMockService = new RenewAccessTokenMockService();
        renewAccessTokenMockService.startMockServer();
        renewAccessTokenMockService.setupRenewAccessTokenMock(accessTokenJSONPath, OK, mockRenewATResponse);

        renewAccessTokenMockTest = new RenewAccessTokenMockTest();
        response = renewAccessTokenMockTest.RenewAccessTokenForMock(OK,accessTokenJSONPath);
        System.out.println(response);
        logger.info("The access token refreshed");

        renewAccessTokenMockService.stopMockServer();
    }

    @And("User can not refresh the access token with empty parameter, Mock Service")
    public void renewAccessToken_emptyParameter_mock(){

        renewAccessTokenMockService = new RenewAccessTokenMockService();
        renewAccessTokenMockService.startMockServer();
        renewAccessTokenMockService.setupRenewAccessTokenMock(emptyJSON, NOT_OK, mockRenewATResponse);

        renewAccessTokenMockTest = new RenewAccessTokenMockTest();
        response = renewAccessTokenMockTest.RenewAccessTokenForMock(NOT_OK,emptyJSON);

        logger.info("The access token can not refreshed");

        renewAccessTokenMockService.stopMockServer();
    }

    @And("User can not refresh the access token with wrong parameter, Mock Service")
    public void renewAccessToken_wrongParameter_mock(){

        renewAccessTokenMockService = new RenewAccessTokenMockService();
        renewAccessTokenMockService.startMockServer();
        renewAccessTokenMockService.setupRenewAccessTokenMock(wrongAccessToken, NOT_OK, mockRenewATResponse);

        renewAccessTokenMockTest = new RenewAccessTokenMockTest();
        response = renewAccessTokenMockTest.RenewAccessTokenForMock(NOT_OK,wrongAccessToken);

        logger.info("The access token can not refreshed");

        renewAccessTokenMockService.stopMockServer();
    }
}