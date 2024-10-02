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

        String responseBody = requestBodyLoader(responseBodyPath);

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
    public void stopMockServer() {
        mockServer.stop();
    }
}
