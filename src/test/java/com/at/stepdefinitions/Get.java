package com.at.stepdefinitions;

import com.at.globalclasses.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.junit.Assert;

public class Get {
    private BasicSecurityUtil base;
    long numberHistories;

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

    @Then("the status code should be {string}")
    public void the_status_code_should_be(String statusCode) {
        int status = Integer.parseInt(statusCode);
        Assert.assertEquals(status, base.ServiceApi.response.getStatusCode().value());
    }

    @Given("I want to retrieve the histories that correspond to this id {string}")
    public void the_user_that_corresponds_to_this_id_has_histories(String id) {
        base.id = id;
    }

    @When("I send a GET request to get the histories")
    public void i_send_a_GET_request_to_get_the_histories() {
        base.response = base.ServiceApi.retrieve(base.ServiceApi.hostName + base.apiResource + base.id);
    }

    @Then("The number of registered histories should be the same of retrieved histories")
    public void the_number_of_registered_histories_should_be_the_same_of_retrieved_histories() {
        MongoDBUtils mongo = new MongoDBUtils();
        numberHistories = mongo.numberOfHistoriesDB(base.environment, base.dataBase, "histories", base.id);

        JSONArray historiesGet = new JSONArray(base.response.getBody());

        Assert.assertEquals(historiesGet.length(), numberHistories);
    }

    @Then("Information retrieved from service should match with DB collection histories")
    public void information_retrieved_from_service_should_match_with_DB_collection_histories() {
        MongoDBUtils mongo = new MongoDBUtils();
        JSONArray dbHistoryArray;
        dbHistoryArray = mongo.arrayHistoryInfoDB(base.environment, base.dataBase, "histories", base.id);

        QAUtils qaUtils = new QAUtils();
        JSONArray getHistoryArray = new JSONArray(base.response.getBody());
        Assert.assertTrue(qaUtils.compareHistoriesDocumentsArrays(dbHistoryArray, getHistoryArray));
    }

    @Then("The number of registered histories should be {int}")
    public void the_number_of_registered_histories_should_be(int histories) {
        MongoDBUtils mongo = new MongoDBUtils();
        numberHistories = mongo.numberOfHistoriesDB(base.environment, base.dataBase, "histories", base.id);

        Assert.assertEquals(histories, numberHistories);
    }

    @Then("Information retrieved from get histories should be an empty list")
    public void information_retrieved_from_service_should_be_an_empty_list() {
        MongoDBUtils mongo = new MongoDBUtils();
        JSONArray dbHistoryArray;
        dbHistoryArray = mongo.arrayHistoryInfoDB(base.environment, base.dataBase, "histories", base.id);

        QAUtils qaUtils = new QAUtils();
        JSONArray getHistoryArray = new JSONArray(base.response.getBody());
        Assert.assertFalse(qaUtils.compareHistoriesDocumentsArrays(dbHistoryArray, getHistoryArray));
    }

}
