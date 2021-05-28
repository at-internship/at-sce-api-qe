package com.at.stepdefinitions;

import com.at.globalclasses.*;
import com.at.globalclasses.domain.QARandomData;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
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
    long numberHistories;

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
        QAUtils.expectedStatus = statusCode;
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

    @Given("I want to retrieve the histories that correspond to this id")
    public void the_user_that_corresponds_to_this_id_has_histories() {
        base.id = MongoDBUtils.getRandomID(base.environment, base.uridb, "users");
    }

    @When("I send a GET request to get the histories")
    public void i_send_a_GET_request_to_get_the_histories() {
        base.response = base.ServiceApi.retrieve(base.ServiceApi.hostName + base.apiResource + base.id);
    }

    @Then("The number of registered histories should be the same of retrieved histories")
    public void the_number_of_registered_histories_should_be_the_same_of_retrieved_histories() {
        MongoDBUtils mongo = new MongoDBUtils();
        numberHistories = mongo.numberOfHistoriesDB(base.environment, base.uridb, "histories", base.id);

        JSONArray historiesGet = new JSONArray(base.response.getBody());

        Assert.assertEquals(historiesGet.length(), numberHistories);
    }

    @Then("Information retrieved from service should match with DB collection histories")
    public void information_retrieved_from_service_should_match_with_DB_collection_histories() {
        MongoDBUtils mongo = new MongoDBUtils();
        JSONArray dbHistoryArray;
        dbHistoryArray = mongo.arrayHistoryInfoDB(base.environment, base.uridb, "histories", base.id);

        QAUtils qaUtils = new QAUtils();
        JSONArray getHistoryArray = new JSONArray(base.response.getBody());
        Assert.assertTrue(qaUtils.compareHistoriesDocumentsArrays(dbHistoryArray, getHistoryArray));
    }

    @Then("The number of registered histories should be {int}")
    public void the_number_of_registered_histories_should_be(int histories) {
        MongoDBUtils mongo = new MongoDBUtils();
        numberHistories = mongo.numberOfHistoriesDB(base.environment, base.uridb, "histories", base.id);

        Assert.assertEquals(histories, numberHistories);
    }

    @Then("Information retrieved from get histories should be an empty list")
    public void information_retrieved_from_get_histories_should_be_an_empty_list() {
        MongoDBUtils mongo = new MongoDBUtils();
        JSONArray dbHistoryArray;
        dbHistoryArray = mongo.arrayHistoryInfoDB(base.environment, base.uridb, "histories", base.id);

        QAUtils qaUtils = new QAUtils();
        JSONArray getHistoryArray = new JSONArray(base.response.getBody());
        Assert.assertFalse(qaUtils.compareHistoriesDocumentsArrays(dbHistoryArray, getHistoryArray));
    }

    @After
    public void scenarios_Name (Scenario scenario) throws Exception {

      if(ExcelUtils.getFromProperties("config.properties","QA.excel-tag").equals("true")) {
          ExcelUtils.reportJson.put("Scenario Status", scenario.getStatus().toString());
          ExcelUtils.reportJson.put("Scenario Name", scenario.getName());
          try{
              ExcelUtils.reportJson.put("Request Body", base.requestBody);}
          catch (Exception e){
              ExcelUtils.reportJson.put("Response Body", "There is no Request Body");}
          try{
          ExcelUtils.reportJson.put("Response Body", base.response.getBody());}
          catch (Exception e){
              ExcelUtils.reportJson.put("Response Body", "There is no Response Body");}
          ExcelUtils.reportJson.put("Expected Status", QAUtils.expectedStatus);
          ExcelUtils.reportJson.put("Current Status", base.response.getStatusCode().value());
          ExcelUtils.reportJson.put("Tags", scenario.getSourceTagNames().toString());
          ExcelUtils.reportJson.put("Path",base.apiResource);
          ExcelUtils.generateArrayJson(ExcelUtils.reportJson);
          ExcelUtils.generateExcelReports();
      }
    }
    @After
    public void scenarios_Info (Scenario scenario) throws Exception {

        scenario.attach(scenario.getName(),"text/plain","Scenario Name");
        scenario.attach(scenario.getStatus().toString(),"text/plain","Scenario Status");
            try{
                scenario.attach(base.requestBody,"text/plain","Request Body");}
                catch (Exception e){
                scenario.attach("There is no Request Body","text/plain","Request Body");
                }
            try{
                scenario.attach(base.response.getBody(),"text/plain","Response Body");}
               catch (Exception e){
                scenario.attach("There is no Response Body","text/plain","Response Body");
                } //
        scenario.attach(QAUtils.expectedStatus,"text/plain","Expected Status");
        scenario.attach(base.response.getStatusCode().toString(),"text/plain","Current Status");
        scenario.attach(scenario.getSourceTagNames().toString(),"text/plain","Tags");
        scenario.attach(base.apiResource,"text/plain","Path");
            }

    @Given("I want to {string} a {string} user")
    public void i_Want_To_Get_A_User(String operation, String data) {

        if (data.equals("invalid")) {
            base.id = QARandomData.randomString();   }
        else{
            base.id= null;        } }


    @And("I am completing the path")
    public void i_Am_Completing_The_Path() {
        if(base.id!=null){
            base.apiResource = base.apiResource+base.id;}



    }
}
