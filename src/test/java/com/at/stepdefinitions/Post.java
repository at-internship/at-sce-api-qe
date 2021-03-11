package com.at.stepdefinitions;

import java.lang.NumberFormatException;
import com.at.globalclasses.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.cucumber.java.en.And;

import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLOutput;
import java.util.Map;

public class Post {
    private BasicSecurityUtil base;

    public Post(BasicSecurityUtil base) {
        this.base = base;
    }

    @Given("I have the following information for new user and build a request body:")
    public void i_have_the_following_information_for_new_user(Map<String, String> userTable) throws Exception {

        int typeValue = Integer.parseInt(userTable.get("type"));
        int statusValue = Integer.parseInt(userTable.get("status"));

        JSONObject jsonBodyRequest = new JSONObject();
        jsonBodyRequest.put("type", typeValue);
        jsonBodyRequest.put("firstName", userTable.get("firstName"));
        jsonBodyRequest.put("lastName", userTable.get("lastName"));
        jsonBodyRequest.put("email", userTable.get("email"));
        jsonBodyRequest.put("password", userTable.get("password"));
        jsonBodyRequest.put("status", statusValue);
        base.requestBody = jsonBodyRequest.toString();

    }
    @Given("I have the following information for new user and build a request body with type or status as null:")
    public void i_have_the_following_information_for_new_user_with_type_or_status_as_null(Map<String,String> userTable) throws Exception {
        JSONObject jsonBodyRequest = new JSONObject();
        jsonBodyRequest.put("type", userTable.get("type"));
        jsonBodyRequest.put("firstName", userTable.get("firstName"));
        jsonBodyRequest.put("lastName", userTable.get("lastName"));
        jsonBodyRequest.put("email", userTable.get("email"));
        jsonBodyRequest.put("password", userTable.get("password"));
        jsonBodyRequest.put("status", userTable.get("status"));
        base.requestBody = jsonBodyRequest.toString();
    }



    @And("I am targeting endpoint for {string}")
    public void i_am_targeting_endpoint_for(String apiPath) {
        for (ApiPath a : ApiPath.values()) {
            if (a.name().equals(apiPath)) {
                base.apiResource = a.getApiPath();
            }
        }
    }

    @When("I send a POST request")
    public void i_send_a_POST_request() {
        base.response = base.ServiceApi.POSTMethod(base.ServiceApi.hostName, base.apiResource, base.requestBody);
    }



}