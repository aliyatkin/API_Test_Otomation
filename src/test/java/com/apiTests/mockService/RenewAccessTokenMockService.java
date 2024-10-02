package com.apiTests.mockService;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static com.apiTests.constants.Endpoint.RENEW_ACCESS_TOKEN_ENDPOINT;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


public class RenewAccessTokenMockService {

    private static ClientAndServer mockServer;

    public void startMockServer() {
        // Start the MockServer
        mockServer = ClientAndServer.startClientAndServer(1080);
    }

    public void setupRenewAccessTokenMock(String accessTokenPath, int statusCode, String responseBodyPath) {

        String responseBody = requestBodyLoader(responseBodyPath);
        String accessToken = requestBodyLoader(accessTokenPath);

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
    public void stopMockServer() {
        mockServer.stop();
    }
}
