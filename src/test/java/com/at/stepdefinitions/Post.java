package com.at.stepdefinitions;

import com.at.globalclasses.*;
import com.at.globalclasses.domain.UserRequest;
import com.google.gson.Gson;

import gherkin.deps.com.google.gson.JsonParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.Map;

public class Post {
    private BasicSecurityUtil base;
    
    private UserRequest userRequest =  new UserRequest();

    public Post(BasicSecurityUtil base) {
        this.base = base;
    }
    
        
   
    @Given("I have the following information for  authenticate a user:")
    public void i_have_the_following_information_for_authenticate_a_user(Map<String, String> dataTable) throws Exception{
    	userRequest.setEmail(dataTable.get("email"));
    	userRequest.setPassword(dataTable.get("password"));
    }

    @Given("I build my request body with information shown above")
    public void i_build_my_request_body_with_information_shown_above() {
        Gson gson =  new Gson();
        base.requestBody = gson.toJson(userRequest);
    }
    @Given("I am targeting endpoint for {string}")
    public void i_am_targeting_endpoint_for(String apiPath) {
        for(ApiPath a: ApiPath.values()) {
        	if(a.name().equals(apiPath)) {
        		base.apiResource = a.getApiPath();
        	}
        }
    }
    @When("I send a POST request")
    public void i_send_a_POST_request() {
    	base.response= base.ServiceApi.POSTMethod(base.ServiceApi.hostName, base.apiResource, base.requestBody);
    }
    
    @Then("The status code should be {string}")
    public void the_status_code_should_be(String statusCode) {
        int status = Integer.parseInt(statusCode);
        Assert.assertEquals(status, base.ServiceApi.response.getStatusCode().value());
    }

    
    @Given("I have acces to the database {string}")
    public void i_have_acces_to_the_database(String database) {
    	MongoDBUtils compare =  new MongoDBUtils();
    	String jsonFromDatabase;
    	jsonFromDatabase = compare.compareJsonFromDatabase(base.environment, base.dataBase, "users",userRequest.getEmail());
    	JsonParser parser = new JsonParser();    	
    	Assert.assertEquals(parser.parse(jsonFromDatabase),parser.parse(base.requestBody));
    }
    

    	@Then("There is not match with any value in DB {string}")
    	public void there_is_not_match_with_any_value_in_DB(String database) {
        	MongoDBUtils compare =  new MongoDBUtils();
        	String jsonFromDatabase;
        	jsonFromDatabase = compare.compareJsonFromDatabase(base.environment, base.dataBase, "users",userRequest.getEmail());
        	Assert.assertEquals("",jsonFromDatabase);
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

    @Given("I have the following information for a new user and build a request body:")
    public void i_have_the_following_information_for_a_new_user_and_build_a_request_body(Map<String,String> userTable) throws Exception {
        //for null values
        JSONObject jsonBodyRequest = new JSONObject();
        jsonBodyRequest.put("type", userTable.get("type"));
        jsonBodyRequest.put("firstName", userTable.get("firstName"));
        jsonBodyRequest.put("lastName", userTable.get("lastName"));
        jsonBodyRequest.put("email", userTable.get("email"));
        jsonBodyRequest.put("password", userTable.get("password"));
        jsonBodyRequest.put("status", userTable.get("status"));
        base.requestBody = jsonBodyRequest.toString();
    }

    @Then("Information retrieved from Post service should match with DB collection {string}")
    public void information_retrieved_from_service_should_match_with_DB_collection(String collection) {

        JSONObject jsonRequest = new JSONObject(base.requestBody);

        MongoDBUtils mongo = new MongoDBUtils();
        String collectionBody = mongo.obtainObject(base.environment, base.dataBase, collection, base.response.getBody());
        JSONObject jsonResponse = new JSONObject(collectionBody);

        boolean bool = mongo.compareUsersDocuments(jsonRequest,jsonResponse);
        Assert.assertTrue(bool);

        JSONObject json = new JSONObject(base.response.getBody());
        String expectedResult = json.getString("id");
        Assert.assertEquals(expectedResult, jsonResponse.getJSONObject("_id").get("$oid").toString());
    }

    


}