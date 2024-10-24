package com.apiTests.mockService;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static com.apiTests.constants.ContentType.*;
import static com.apiTests.constants.Endpoint.LOGIN_ENDPOINT;
import static com.apiTests.constants.HttpMethod.POST;
import static com.apiTests.constants.Language.*;
import static com.apiTests.constants.Numbers.portNumber;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class LoginMockService {

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
     * This method sets up the login mock service with the specified parameters
     *
     * @param statusCode                The status code that the MockServer should return
     * @param mockResponseBodyPath      The path to the file containing the mock response body
     * @param mockRequestBodyPath       The path to the file containing the expected request body
     * @param torf                      A string representing "True or False" for query parameter
     */
    public void setupLoginMock(int statusCode, String mockRequestBodyPath, String mockResponseBodyPath,  String torf) {

        // Load the response and request body from the specified file paths
        String mockReqBody = requestBodyLoader(mockRequestBodyPath);
        String mockRespBody = requestBodyLoader(mockResponseBodyPath);

        // Mocking a POST request and providing a predefined response for testing,
        // without needing actual server interaction.
        new MockServerClient("localhost", portNumber)
                .when(request()
                        .withMethod(POST)
                        .withPath(LOGIN_ENDPOINT)
                        .withQueryStringParameter("basic", torf)
                        .withHeader(contentType, json)
                        .withHeader(language, en)
                        .withBody(mockReqBody)
                )
                .respond(response()
                        .withStatusCode(statusCode)
                        .withHeader(contentType, json)
                        .withBody(mockRespBody)
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