package com.apiTests.constants;

public class Endpoint {

    // user-controller service
    public static final String LOGIN_ENDPOINT = "/v1/login";
    public static final String LOGOUT_ENDPOINT = "/v1/logout";
    public static final String RENEW_ACCESS_TOKEN_ENDPOINT = "/v1/renew-access-token";

    // detection-controller service
    public static final String DETECTIONS_ENDPOINT = "/v1/detections";
    public static final String CLASSIFICATION_ENDPOINT = "/v1/detections/classification-types";
    public static final String ZONES_ENDPOINT = "v1/zones";
}
