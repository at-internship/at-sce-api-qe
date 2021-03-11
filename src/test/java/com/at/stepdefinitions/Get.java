package com.at.stepdefinitions;

import com.at.globalclasses.*;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;

public class Get {
    private BasicSecurityUtil base;

    public Get(BasicSecurityUtil base) {
        this.base = base;
    }

    @Given("I am working on {string} environment")
    public void i_am_working_on_environment(String env) throws Exception {
        base.environment = env;
    }

    @Given("I am targeting {string} service")
    public void i_am_targeting_service(String service) throws Exception {
        base.ServiceApi = new ApiTools(base.environment, service);
    }

    @Given("I have access to {string} database")
    public void i_have_access_to_database(String dataBase) throws Exception {
        base.dataBase = dataBase;

    }

    @Given("I want to retrieve all users")
    public void i_want_to_retrieve_all_users() throws Exception {
        base.apiResource = ApiPaths.SCE_GET_USERS;
    }

    @When("I send a GET request")
    public void i_send_a_GET_request() {
        base.response = base.ServiceApi.retrieve(base.ServiceApi.hostName + base.apiResource);
    }

    @Then("The status code should be {string}")
    public void the_status_code_should_be(String statusCode) {
        int status = Integer.parseInt(statusCode);
            Assert.assertEquals(status, base.ServiceApi.response.getStatusCode().value());

    }

    @Then("Information retrieved from service should match with DB collection {string}")
    public void information_retrieved_from_service_should_match_with_DB_collection(String collection) {

        JSONObject jsonRequest = new JSONObject(base.requestBody);

        MongoDBUtils mongo = new MongoDBUtils();
        String collectionBody = mongo.obtainObject(base.environment, base.dataBase, collection, base.response.getBody());
        JSONObject jsonResponse = new JSONObject(collectionBody);

        boolean bool = mongo.compareDocuments(jsonRequest,jsonResponse);
        Assert.assertTrue(bool);

        JSONObject json = new JSONObject(base.response.getBody());
        String expectedResult = json.getString("id");
        Assert.assertEquals(expectedResult, jsonResponse.getJSONObject("_id").get("$oid").toString());
    }

}
