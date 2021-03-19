package com.at.stepdefinitions;

import com.at.globalclasses.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import gherkin.deps.com.google.gson.JsonParser;
import org.junit.Assert;

public class Get {
    private BasicSecurityUtil base;
    long numberUsers;

    public Get(BasicSecurityUtil base, BasicSecurityUtil basedb) {
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
    public void iHaveAccessToDatabase(String dataBase) {
        base.uridb = dataBase;

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

    @Then("the number of registered users should be the same of the number of retrieved users")
    public void the_number_of_registered_users_should_be_the_same_of_the_number_of_retrieved_users() {
        QAUtils usersMongo = new QAUtils();

        numberUsers = usersMongo.numberOfUsersDB(base.environment, base.uridb, "users");
        System.out.println("The number of registered users in MongoDB is: " + numberUsers);

        ApiTools api = new ApiTools(base.environment, base.apiResource);
        String usersInfoString = api.retrieve(base.ServiceApi.hostName + base.apiResource).getBody();
        JSONArray userGet = new JSONArray(usersInfoString);
        System.out.println("The number of users retrieved by the GET operation is: " + userGet.length());
        if (userGet.length() == numberUsers) {
            System.out.println("The number of users match.");
        }

    }

    @Then("information retrieved from service should match with DB")
    public void information_retrieved_from_service_should_match_with_DB() {
        QAUtils Users = new QAUtils();
        JSONArray firstArray = new JSONArray();
        firstArray = Users.arrayUsersInfoDB(base.environment, base.uridb, "users");

        ApiTools api = new ApiTools(base.environment, base.apiResource);
        String usersInfoString = api.retrieve(base.ServiceApi.hostName + base.apiResource).getBody();
        JSONArray secondArray = new JSONArray(usersInfoString);

        System.out.println("Users info in the DB: " + firstArray.toString());
        System.out.println("Users info in the GET op: " + secondArray.toString());
        QAUtils match = new QAUtils();
        match.setNumberUsersDB(numberUsers);
        boolean bool = match.compareDocumentsArrays(firstArray, secondArray);
        Assert.assertTrue(bool);
        System.out.println("The values of all users match.");
    }

    @Given("I want to retrieve 0 registered users")
    public void i_want_to_retrieve_0_registered_users() throws Exception {
        base.apiResource = ApiPaths.SCE_GET_USERS;
    }

    @Then("the number of registered users should be 0")
    public void the_number_of_registered_users_should_be_0() {

        QAUtils usersMongo = new QAUtils();
        numberUsers = usersMongo.numberOfUsersDB(base.environment, base.uridb, "users");
        System.out.println("The number of registered users in MongoDB is: " + numberUsers);

        ApiTools api = new ApiTools(base.environment, base.apiResource);
        String usersInfoString = api.retrieve(base.ServiceApi.hostName + base.apiResource).getBody();
        JSONArray userGet = new JSONArray(usersInfoString);
        System.out.println("The number of users retrieved by the GET operation is: " + userGet.length());
        if (userGet.length() == numberUsers) {
            System.out.println("The number of users match.");
        }

    }

    @Then("information retrieved from service should be an empty list")
    public void information_retrieved_from_service_should_be_an_empty_list() {
        QAUtils Users = new QAUtils();
        JSONArray firstArray = new JSONArray();
        firstArray = Users.arrayUsersInfoDB(base.environment, base.uridb, "users");

        ApiTools api = new ApiTools(base.environment, base.apiResource);
        String usersInfoString = api.retrieve(base.ServiceApi.hostName + base.apiResource).getBody();
        JSONArray secondArray = new JSONArray(usersInfoString);

        System.out.println("Users info in the DB: " + firstArray.toString());
        System.out.println("Users info in the GET op: " + secondArray.toString());
        QAUtils match = new QAUtils();
        match.setNumberUsersDB(numberUsers);
        boolean bool = match.compareDocumentsArrays(firstArray, secondArray);
        Assert.assertTrue(bool);
        System.out.println("The values of all users match.");
    }
}

