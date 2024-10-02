package com.apiTests.mockService;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static com.apiTests.constants.Endpoint.LOGIN_ENDPOINT;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class LoginMockService {

    private static ClientAndServer mockServer;

    public void startMockServer() {

        // Start the MockServer
        mockServer = ClientAndServer.startClientAndServer(1080);
    }
    // torf = Shortcut of "True or False"
    public void setupLoginMock(int statusCode, String mockResponseBodyPath, String requestBodyPath, String torf) {

        // It goes to the paths given to the method and writes the String inside those paths to a String
        String mockRespBody = requestBodyLoader(mockResponseBodyPath);
        String mockReqBody = requestBodyLoader(requestBodyPath);

        // The purpose of this code is to mock a POST request and provide a predefined response for testing,
        // without needing the actual server interaction.
        new MockServerClient("localhost", 1080)
                .when(request()
                        .withMethod("POST")
                        .withPath(LOGIN_ENDPOINT)
                        .withQueryStringParameter("basic", torf)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Accept-Language", "en")
                        .withBody(mockReqBody)
                )
                .respond(response()
                        .withStatusCode(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(mockRespBody)
                );
    }

    // Stop the MockServer
    public void stopMockServer() {
        mockServer.stop();
    }
}