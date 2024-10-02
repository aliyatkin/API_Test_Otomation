package com.apiTests.mockService;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static com.apiTests.constants.Endpoint.LOGOUT_ENDPOINT;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class LogoutMockService {

    private static ClientAndServer mockServer;

    public void startMockServer() {

        // Start the MockServer
        mockServer = ClientAndServer.startClientAndServer(1080);
    }

    public void setupLogoutMock(String accessToken, int statusCode, String responseBodyPath){

        // It goes to the paths given to the method and writes the String inside those paths to a String
        String responseBody = requestBodyLoader(responseBodyPath);

        // The purpose of this code is to mock a POST request and provide a predefined response for testing,
        // without needing the actual server interaction.
        new MockServerClient("localhost", 1080)
                .when(request()
                        .withMethod("POST")
                        .withPath(LOGOUT_ENDPOINT)
                        .withHeader("Authorization", "Bearer " + accessToken)
                )
                .respond(response()
                        .withStatusCode(statusCode)
                        .withHeader("Content-Type", "text/plain;charset=UTF-8")
                        .withBody(responseBody)

                );
    }

    // Stop the MockServer
    public void stopMockServer() {
        mockServer.stop();
    }
}