package com.apiTests.mockService;

import org.mockserver.integration.ClientAndServer;

public class RenewAccessTokenMockService {

    private static ClientAndServer mockServer;

    public void startMockServer() {
        // Start the MockServer
        mockServer = ClientAndServer.startClientAndServer(1080);
    }

    public void setupRenewAccessTokenMock(){


    }
}
