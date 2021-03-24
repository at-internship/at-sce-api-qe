package com.at.globalclasses;

import org.springframework.http.ResponseEntity;

public class BasicSecurityUtil {
    public String environment, requestBody, responseBody, apiResource,dataBase;
    public ResponseEntity<String> response;
    public ApiTools ServiceApi;
    public String type;
    public String method;
    public String expectedMessage;
    public String param;
    public String id;
}
