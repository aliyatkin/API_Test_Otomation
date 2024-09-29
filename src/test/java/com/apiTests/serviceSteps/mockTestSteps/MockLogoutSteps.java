package com.apiTests.serviceSteps.mockTestSteps;

import com.apiTests.mockService.LoginMockService;
import com.apiTests.mockService.LogoutMockService;
import com.apiTests.models.user_controller.login.LoginResponse;
import com.apiTests.requests.mockRequests.LoginMockTest;
import com.apiTests.requests.mockRequests.LogoutMockTest;
import com.apiTests.serviceSteps.serviceSteps.LoginSteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.apiTests.constants.Data_Path.*;
import static com.apiTests.constants.StatusCode.NOT_OK;
import static com.apiTests.constants.StatusCode.OK;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;

public class MockLogoutSteps {

    ObjectMapper objectMapper = new ObjectMapper();

    private LoginResponse loginResponse;
    private String accessToken;
    private LoginMockTest loginMockTests;
    private LogoutMockTest logoutMockTest;
    private Response response;
    private static LoginMockService loginMockService;
    private static LogoutMockService logoutMockService;
    private static final Logger logger = LogManager.getLogger(MockLogoutSteps.class);

    @Given("User logs into the system with a valid username and password for logout tests, Mock Service")
    public void validUsernameAndPasswordForMock_Logout() {

        String mockRespBodyPath = mockResponseBody;
        String mockReqBodyPath = validUsernameAndPassword;

        loginMockService = new LoginMockService();
        loginMockService.startMockServer();  // Mock sunucuyu başlat
        loginMockService.setupLoginMock(OK, mockRespBodyPath, mockReqBodyPath, "true");   // Mock yanıtını ayarla

        // Read the data from JSON file and save it in requestBody
        loginMockTests = new LoginMockTest();  // Create the LoginTests class for Login
        loginResponse = loginMockTests.LoginForMock(validUsernameAndPassword,OK,true);  // Save the response in loginResponse

        logger.info("The system has not been logged in with a valid username and password");
    }

    @When("Verifying the required response parameters for logout tests, Mock Service")
    public void checkParameterForMock_Logout() throws JsonProcessingException {

        String username = loginResponse.getUser().getUsername();
        String requestBody = requestBodyLoader(validUsernameAndPassword);
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        // Username check
        if (jsonNode.get("username").asText().equals(username)) {
            logger.info("The username in the response matches the one in the request");
            Allure.addAttachment("username check","The username in the response matches the one in the request");
        } else {
            logger.error("The username in the response does not match the one in the request");
            Allure.addAttachment("username check","The username in the response does not matches the one in the request");
            Assertions.fail("The username in the response does not match the one in the request");
        }
    }

    @Then("Save the access token for logout tests, Mock Service")
    public void saveAccessTokenForMock_Logout() {
        // Save access token
        accessToken = loginResponse.getTokenDetails().getAccessToken();
        logger.info("The Access Token has been saved: " + accessToken);
        Allure.addAttachment("Access Token", accessToken);

        loginMockService.stopMockServer();  // Mock sunucuyu durdur
    }

    @And("User logs out from the system with the saved access token, Mock Service")
    public void logoutForMock(){

        logoutMockService = new LogoutMockService();
        logoutMockService.startMockServer();  // Mock sunucuyu başlat
        logoutMockService.setupLogoutMock(accessToken, NOT_OK, mockLogoutResponse );   // Mock yanıtını ayarla

        logoutMockTest = new LogoutMockTest();
        response = logoutMockTest.LogoutForMock(accessToken, NOT_OK);
    }
}
