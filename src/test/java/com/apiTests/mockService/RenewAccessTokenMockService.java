package com.apiTests.mockService;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static com.apiTests.constants.Endpoint.RENEW_ACCESS_TOKEN_ENDPOINT;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


public class RenewAccessTokenMockService {

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
     * This method sets up the renew access token mock service with the specified parameters.
     *
     * @param statusCode            The status code that the MockServer should return
     * @param accessTokenPath       The path to the file containing the access token
     * @param responseBodyPath      The path to the file containing the mock response body
     */
    public void setupRenewAccessTokenMock(int statusCode, String accessTokenPath,  String responseBodyPath) {

        // Load the access token and response body from the specified file paths
        String accessToken = requestBodyLoader(accessTokenPath);
        String responseBody = requestBodyLoader(responseBodyPath);

        // Mocking a POST request and providing a predefined response for testing,
        // without needing actual server interaction.
        new MockServerClient("localhost", 1080)
                .when(request()
                        .withMethod("POST")
                        .withPath(RENEW_ACCESS_TOKEN_ENDPOINT)
                        .withHeader("Authorization", "Bearer" + accessToken)
                )
                .respond(response()
                        .withStatusCode(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)
                );
    }

    /**
     * Stops the MockServer instance.
     */
    public void stopMockServer() {
        // Stop the MockServer
        mockServer.stop();
    }
}