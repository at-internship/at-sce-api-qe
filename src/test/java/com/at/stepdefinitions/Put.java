package com.at.stepdefinitions;

import com.at.globalclasses.*;
import com.at.globalclasses.domain.UserRequest;
import com.google.gson.Gson;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import gherkin.deps.com.google.gson.JsonParser;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;


import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Put {

    private BasicSecurityUtil base;

    private UserRequest userRequest = new UserRequest();

    public Put(BasicSecurityUtil base) {
        this.base = base;
    }

    @Before("@US_020 and not @2")
    public void create_body_for_a_new_user(){
        Lorem lorem = LoremIpsum.getInstance();
        JSONObject jsonBodyRequest = new JSONObject();
        jsonBodyRequest.put("type", 1);
        jsonBodyRequest.put("firstName", lorem.getFirstName().toLowerCase());
        jsonBodyRequest.put("lastName", lorem.getLastName().toLowerCase());
        jsonBodyRequest.put("email", lorem.getFirstName().toLowerCase()+"@gmail.com");
        jsonBodyRequest.put("password", lorem.getFirstName().toLowerCase()+"12345678");
        jsonBodyRequest.put("status", 0);
        base.requestBody = jsonBodyRequest.toString();
        base.apiResource = ApiPath.updating_users.getApiPath();;
    }

    @Given("I get the id of a new user")
    public void i_get_a_id_user_randomly() {
    	
        base.response= base.ServiceApi.POSTMethod(base.ServiceApi.hostName, base.apiResource, base.requestBody);
        JSONObject json = new JSONObject(base.response.getBody());
        base.id = json.getString("id");
    }

    @Given("This id doesn't belong to any user {string}")
    public void this_id_doesn_t_belong_to_any_user(String idValue) {
        base.id = idValue;
    }

    @Given("I use the id of the new user")
    public void I_use_the_id_of_the_new_user() {
        JSONObject json = new JSONObject(base.response.getBody());
        base.id = json.getString("id");
    }


    @Given("I have the following information to update user by id and build a request body:")
    public void i_have_the_following_information_to_update_user_by_id_and_build_a_request_body(Map<String, String> userTable) {
        JSONObject jsonBodyRequest = new JSONObject();

        try {
            jsonBodyRequest.put("type", Integer.parseInt(userTable.get("type")));
        } catch (NumberFormatException e) {
            jsonBodyRequest.put("type", userTable.get("type"));
        }

        try {
            jsonBodyRequest.put("status", Integer.parseInt(userTable.get("status")));
        } catch (NumberFormatException e) {
            jsonBodyRequest.put("status", userTable.get("status"));
        }

        jsonBodyRequest.put("firstName", userTable.get("firstName"));
        jsonBodyRequest.put("lastName", userTable.get("lastName"));
        jsonBodyRequest.put("email", userTable.get("email"));
        jsonBodyRequest.put("password", userTable.get("password"));
        base.requestBody = jsonBodyRequest.toString();
    }

 
    
    @And("the value of firstName is {int}")
	public void the_value_of_firstName_is(int firstName) {
	    JSONObject jsonObject = new JSONObject(base.requestBody);
	    jsonObject.put("firstName",firstName);
	    base.requestBody = jsonObject.toString();
	    System.out.println("Body request: "+base.requestBody);
	}

	@And("the value of lastName is {int}")
	public void the_value_of_lastName_is(int lastName) {
	    JSONObject jsonObject = new JSONObject(base.requestBody);
	    jsonObject.put("lastName",lastName);
	    base.requestBody = jsonObject.toString();
	    System.out.println("Body request: "+base.requestBody);
	}

	@And("the value of email is {int}")
	public void the_value_of_email_is(int email) {
	    JSONObject jsonObject = new JSONObject(base.requestBody);
	    jsonObject.put("email",email);
	    base.requestBody = jsonObject.toString();
	    System.out.println("Body request: "+base.requestBody);
	}

	@And("the value of password is {int}")
	public void the_value_of_password_is(int password) {
	    JSONObject jsonObject = new JSONObject(base.requestBody);
	    jsonObject.put("password",password);
	    base.requestBody = jsonObject.toString();
	    System.out.println("Body request: "+base.requestBody);
	}
    @When("I send a PUT request")
    public void i_send_a_PUT_request() {
        base.response = base.ServiceApi.PUTMethod(base.ServiceApi.hostName + base.apiResource + base.id, base.requestBody);
    }

    @Then("The status code of the result should be {string}")
    public void the_status_code_of_the_result_should_be(String statusCode) {
        int status = Integer.parseInt(statusCode);
        Assert.assertEquals(status, base.ServiceApi.response.getStatusCode().value());
    }

    @Then("Information from response body should match with DB collection {string}")
    public void information_from_response_body_should_match_with_DB_collection(String collection) {
        JSONObject jsonResponseBody = new JSONObject(base.response.getBody());

        MongoDBUtils mongo = new MongoDBUtils();
        String collectionBody = mongo.obtainObject(base.environment, base.dataBase, collection, base.response.getBody());
        JSONObject jsonCollection = new JSONObject(collectionBody);

        boolean boolDocuments = mongo.compareUsersDocuments(jsonResponseBody, jsonCollection);
        Assert.assertTrue(boolDocuments);

        JSONObject jsonRequest = new JSONObject(base.requestBody);
        boolean boolPassword = mongo.compareUsersPasswords(jsonRequest, jsonCollection);
        Assert.assertTrue(boolPassword);

        JSONObject json = new JSONObject(base.response.getBody());
        String expectedResult = json.getString("id");
        Assert.assertEquals(expectedResult, jsonCollection.getJSONObject("_id").get("$oid").toString());
    }

    @Then("Information from response body should match with error {string} message")
    public void information_from_response_body_should_match_with_error_not_found_message(String error){
        JSONObject json = new JSONObject(Objects.requireNonNull(base.response.getBody()));
        String errorMessage= json.getString("error");
        Assert.assertEquals(errorMessage,error);
    }

}
