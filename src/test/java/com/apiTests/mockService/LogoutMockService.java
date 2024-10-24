package com.apiTests.mockService;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static com.apiTests.constants.ContentType.*;
import static com.apiTests.constants.Endpoint.LOGOUT_ENDPOINT;
import static com.apiTests.constants.HttpMethod.POST;
import static com.apiTests.constants.Language.*;
import static com.apiTests.constants.Numbers.portNumber;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class LogoutMockService {

    // The mockServer variable belonging to the ClientAndServer class is being defined
    private static ClientAndServer mockServer;

    /**
     * This method starts the MockServer on port 1080 and assigns it to the mockServer variable.
     */
    public void startMockServer() {

        // MockServer is started on port 1080 and assigned to the mockServer variable
        mockServer = ClientAndServer.startClientAndServer(1080);
    }

    /**
     * This method sets up the logout mock service with the specified parameters.
     *
     * @param statusCode                The status code that the MockServer should return
     * @param accessTokenPath           The path to the file containing the access token
     * @param mockResponseBodyPath      The path to the file containing the mock response body
     */
    public void setupLogoutMock( int statusCode, String accessTokenPath, String mockResponseBodyPath){

        // Load the access token and response body from the specified file paths
        String accessToken = requestBodyLoader(accessTokenPath);
        String responseBody = requestBodyLoader(mockResponseBodyPath);

        // Mocking a POST request and providing a predefined response for testing,
        // without needing actual server interaction.
        new MockServerClient("localhost", portNumber)
                .when(request()
                        .withMethod(POST)
                        .withPath(LOGOUT_ENDPOINT)
                        .withHeader("Authorization", "Bearer " + accessToken)
                        .withHeader(language, en)
                )
                .respond(response()
                        .withStatusCode(statusCode)
                        .withHeader(contentType, charset)
                        .withBody(responseBody)
                );
    }

    /**
     * Stops the MockServer instance.
     */
    public void stopMockServer()
    {
        // Stop the MockServer
        mockServer.stop();
    }
}