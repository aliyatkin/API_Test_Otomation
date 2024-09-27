package com.apiTests.mockService;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class LoginMockService {

    private static ClientAndServer mockServer;

    public void startMockServer() {
        // Start the MockServer
        mockServer = ClientAndServer.startClientAndServer(1080);
    }


    public void setupLoginMock(int statusCode, String mockResponseBodyPath, String requestBodyPath) {

        String mockRespBody = requestBodyLoader(mockResponseBodyPath);
        String mockReqBody = requestBodyLoader(requestBodyPath);

        new MockServerClient("localhost", 1080)
                .when(request()
                        .withMethod("POST")
                        .withPath("/v1/login")
                        .withQueryStringParameter("basic", "true")
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

    public void stopMockServer() {
        mockServer.stop();
    }
}
