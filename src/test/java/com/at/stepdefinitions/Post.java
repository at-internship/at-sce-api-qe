package com.at.stepdefinitions;

import com.at.globalclasses.*;
import com.at.globalclasses.domain.FixedERequest;
import com.at.globalclasses.domain.HistoriesRequest;
import com.at.globalclasses.domain.QARandomData;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Base64;
import java.util.Map;

public class Post {

    private BasicSecurityUtil base;


    private UserRequest userRequest = new UserRequest();
    private HistoriesRequest historyData = new HistoriesRequest();
    private FixedERequest fixedExpenses = new FixedERequest();
    private JSONObject parentData = new JSONObject();
    private JSONObject childData = new JSONObject();
    JSONObject historyJson;
    JSONObject fixedExpensesJson;
    JSONObject userJson;
    Lorem lorem = new LoremIpsum().getInstance();
    QARandomData randomCategory = new QARandomData();




    public Post(BasicSecurityUtil base) {
        this.base = base;
    }



    @Given("I have the following information for  authenticate a user:")
    public void i_have_the_following_information_for_authenticate_a_user(Map<String, String> dataTable) throws Exception {
        userRequest.setEmail(dataTable.get("email"));
        userRequest.setPassword(dataTable.get("password"));    }

    @Given("I build my request body with information shown above")
    public void i_build_my_request_body_with_information_shown_above() {
        Gson gson = new Gson();
        base.requestBody = gson.toJson(userRequest);
    }

    @Given("I am targeting endpoint for {string}")
    public void i_am_targeting_endpoint_for(String apiPath) {
        for (ApiPath a : ApiPath.values()) {
            if (a.name().equals(apiPath)) {
                base.apiResource = a.getApiPath();
            }
        }
    }

    @When("I send a POST request to {string}")
    public void i_send_a_POST_request(String type) {

        if(type.equals("login")){
            base.response = base.ServiceApi.POSTMethod(base.ServiceApi.hostName, base.apiResource, userJson.get("email").toString(),userJson.get("password").toString());
        }
        if(type.equals("create")) {
            base.response = base.ServiceApi.POSTMethod(base.ServiceApi.hostName, base.apiResource, base.requestBody);
        }
        }

    @Then("The status code should be {string}")
    public void the_status_code_should_be(String statusCode) {
        int status = Integer.parseInt(statusCode);
        QAUtils.expectedStatus = statusCode;
        Assert.assertEquals(status, base.ServiceApi.response.getStatusCode().value());
    }


    @Given("I have acces to the database {string}")
    public void i_have_acces_to_the_database(String database) {
        MongoDBUtils compare = new MongoDBUtils();
        String jsonFromDatabase;
        jsonFromDatabase = compare.compareJsonFromDatabase(base.environment, base.dataBase, "users", userRequest.getEmail());
        JsonParser parser = new JsonParser();
        Assert.assertEquals(parser.parse(jsonFromDatabase), parser.parse(base.requestBody));
    }


    @Then("There is not match with any value in DB {string}")
    public void there_is_not_match_with_any_value_in_DB(String database) {
        MongoDBUtils compare = new MongoDBUtils();
        String jsonFromDatabase;
        jsonFromDatabase = compare.compareJsonFromDatabase(base.environment, base.dataBase, "users", userRequest.getEmail());
        Assert.assertEquals("", jsonFromDatabase);
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
    public void i_have_the_following_information_for_a_new_user_and_build_a_request_body(Map<String, String> userTable) throws Exception {
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
        String collectionBody = mongo.obtainObject(base.environment, base.uridb, collection, base.response.getBody());
        JSONObject jsonResponse = new JSONObject(collectionBody);

        boolean bool = mongo.compareUsersDocuments(jsonRequest,jsonResponse);

        Assert.assertTrue(bool);

        JSONObject json = new JSONObject(base.response.getBody());
        String expectedResult = json.getString("id");
        Assert.assertEquals(expectedResult, jsonResponse.getJSONObject("_id").get("$oid").toString());
    }

    @Given("I want to login a user with the next information:")
    public void i_Want_To_Login_A_User_With_The_Next_Information(Map<String, String> dataTable) throws Exception {
        userRequest.setEmail(dataTable.get("email"));
        userRequest.setPassword(dataTable.get("password"));
    }

    @And("I have the {string} body response")
    public void i_Have_The_Body_Response(String body) throws Exception {

        if (body.equals("correct")) {
            JSONObject correctResponse = new JSONObject(base.ServiceApi.response.getBody());
            String id = correctResponse.getString("_id");

            MongoDBUtils userDB = new MongoDBUtils();
            JSONObject userToCompare = new JSONObject(userDB.getJObjectByID(base.environment, base.uridb, "users", id));

            Assert.assertEquals(userJson.get("email").toString(), userToCompare.getString("email"));
        }
        if (body.equals("failure")) {
            JSONObject failureResponse = new JSONObject(base.ServiceApi.response.getBody());

        Assert.assertTrue(failureResponse.get("error").equals("unauthorized") || failureResponse.get("error").equals("server_error") || failureResponse.get("error").equals("invalid_request"));
        Assert.assertTrue(failureResponse.get("error_description").equals("Internal Server Error") || failureResponse.get("error_description").equals("Invalid login credentials.") || failureResponse.get("error_description").equals("Missing grant type"));

           }


    }

    @Given("I want to login a user with the status {string}")
    public void i_Want_To_Login_A_User_With_The_Status(String status) throws Exception {
        MongoDBUtils randomUser = new MongoDBUtils();
        String actualStatus;
        if (status.equals("available")) {
            do {
                String id = randomUser.getRandomID(base.environment, base.uridb, "users");
                JSONObject userData = new JSONObject(randomUser.getJObjectByID(base.environment, base.uridb, "users", id));
                userRequest.setEmail(userData.getString("email"));
                userRequest.setPassword(userData.getString("password"));
                actualStatus = userData.get("status").toString();
            } while (!actualStatus.equals("1"));
        }
        if (status.equals("unavailable")) {
            do {
                String id = randomUser.getRandomID(base.environment, base.uridb, "users");
                JSONObject userData = new JSONObject(randomUser.getJObjectByID(base.environment, base.uridb, "users", id));
                userRequest.setEmail(userData.getString("email"));
                userRequest.setPassword(userData.getString("password"));
                actualStatus = userData.get("status").toString();
            } while (!actualStatus.equals("0"));

        }
    }


    @Before("@US_030 or @US_038")
    public void create_body_for_a_new_history() {

        historyData.setType(randomCategory.correctRangeInt(1, 4));
        historyData.setCostDay(randomCategory.positiveDouble());
        historyData.setCostHour(randomCategory.positiveDouble());
        historyData.setProjectCost(randomCategory.positiveDouble());
        historyData.setRevenue(randomCategory.positiveDouble());
        historyData.setStatus(randomCategory.correctRangeInt(0, 1));
        historyData.setTaxISR_r(randomCategory.positiveDouble());
        historyData.setTaxIVA(randomCategory.positiveDouble());
        historyData.setTaxIVA_r(randomCategory.positiveDouble());
        historyData.setTotal(randomCategory.positiveDouble());
        historyData.setTotalDays(randomCategory.positiveInt());
        historyData.setTotalHours(randomCategory.positiveInt());
        historyData.setUser_id(randomCategory.randomString());
        historyJson = new JSONObject(historyData);
        fixedExpenses.setFeed(randomCategory.positiveDouble());
        fixedExpenses.setInternet(randomCategory.positiveDouble());
        fixedExpenses.setOthers(randomCategory.positiveDouble());
        fixedExpenses.setRent(randomCategory.positiveDouble());
        fixedExpenses.setTransport(randomCategory.positiveDouble());
        fixedExpenses.setTotal(fixedExpenses.getFeed()+ fixedExpenses.getInternet()+ fixedExpenses.getRent()+ fixedExpenses.getTransport()+ fixedExpenses.getOthers());
        fixedExpensesJson = new JSONObject(fixedExpenses);

    }

    @Given("I want to create a new history with {string} {string}")
    public void i_Want_To_Create_A_New_History_With(String field, String data) {
        QAUtils qaUtils = new QAUtils();
        MongoDBUtils randomUser = new MongoDBUtils();

        historyData.setUser_id(randomUser.getRandomID(base.environment, base.uridb, "users"));
        historyJson.put("user_id", historyData.getUser_id());
        historyJson = qaUtils.fillHistoryBody(field, data, historyJson);

    }

    @And("I have the fixed expenses with {string} {string}")
    public void i_Have_The_Fixed_Expenses_With(String field, String data) {
        QAUtils qaUtils = new QAUtils();
        fixedExpensesJson = qaUtils.fillFixExpBody(field, data, fixedExpensesJson);

    }

    @And("I build my request body with the information of the history")
    public void i_Build_My_Request_Body_With_The_Information_Of_The_History() {
        historyJson.put("fixedExpenses", fixedExpensesJson);
        base.requestBody = historyJson.toString();

    }

    @And("The history created match with the history in the data base")
    public void the_History_Created_Match_With_The_History_In_The_Data_Base() {
        MongoDBUtils mongo = new MongoDBUtils();
        JSONObject idJson = new JSONObject(base.response.getBody());
        QAUtils qaUtils = new QAUtils();
        base.id = idJson.getString("id");
        String dbBody = mongo.obtainObject(base.environment, base.uridb, "histories", idJson.toString());

        JSONObject jsonHistoryDB = new JSONObject(dbBody);
        String expectedId = jsonHistoryDB.getJSONObject("_id").get("$oid").toString();
        jsonHistoryDB = qaUtils.modifiedJsonHistory(jsonHistoryDB);

        Assert.assertEquals(base.id, expectedId);
        JSONAssert.assertEquals(historyJson, jsonHistoryDB, false);

    }

    @And("I have the incorrect body response for the history")
    public void i_Have_The_Incorrect_Body_Response_For_The_History() {
        JSONObject failureResponse = new JSONObject(base.response.getBody());
        QAUtils validation = new QAUtils();

        Assert.assertTrue(validation.validateRegex(QAUtils.timestamp, failureResponse.get("timestamp").toString()));
        Assert.assertEquals("Bad Request", failureResponse.get("error"));
        Assert.assertEquals("400",failureResponse.get("status").toString());
        Assert.assertTrue(validation.validateRegex("JSON parse error", failureResponse.get("message").toString())
                || validation.validateRegex("Invalid input on field", failureResponse.get("message").toString())
                || validation.validateRegex("Required field", failureResponse.get("message").toString())
                || validation.validateRegex("The given id ", failureResponse.get("message").toString()));
        Assert.assertEquals("/api/v1/histories", failureResponse.get("path"));
    }

  @Given("I have the following parent information for  create  a new history:")
	public void i_have_the_following_parent_information_for_create_a_new_history(Map<String, Double> parent)
			throws Exception {
		parentData.put("_id", parent.get("_id"));
		parentData.put("type", parent.get("type"));
		parentData.put("user_id", parent.get("user_id").toString());
		parentData.put("totalHours", parent.get("totalHours"));
		parentData.put("totalDays", parent.get("totalDays"));
		parentData.put("costDay", parent.get("costDay"));
		parentData.put("costHour", parent.get("costHour"));
		parentData.put("projectCost", parent.get("projectCost"));
		parentData.put("taxIVA", parent.get("taxIVA"));
		parentData.put("taxISR_r", parent.get("taxISR_r"));
		parentData.put("taxIVA_r", parent.get("taxIVA_r"));
		parentData.put("total", parent.get("total"));
		parentData.put("revenue", parent.get("revenue"));
		parentData.put("status", parent.get("status"));
	}

	@Given("I have the following fixed expenses for  create  a new history:")
	public void i_have_the_following_fixed_expenses_for_create_a_new_history(Map<String, Double> expenses)
			throws Exception {

		childData.put("rent", expenses.get("rent"));
		childData.put("transport", expenses.get("transport"));
		childData.put("internet", expenses.get("internet"));
		childData.put("feed", expenses.get("feed"));
		childData.put("others", expenses.get("others"));
		childData.put("total", expenses.get("total"));
	}

	@Given("I create a request body with the above information")
	public void i_create_a_request_body_with_the_above_information() {

		try {
			parentData.put("fixedExpenses", childData);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		base.requestBody = parentData.toString();
	}

	@Then("Information retrieved from Post operation should match with the collection {string}")
	public void information_retrieved_from_Post_operation_should_match_with_the_collection(String collection) {
		base.collection = collection;

		String response = base.ServiceApi.response.getBody();
		JSONObject jsonObject = new JSONObject(response);

		MongoDBUtils mongoDBUtils = new MongoDBUtils();
		String objectFromDatabase = mongoDBUtils.getJObjectByID(base.environment, base.uridb, base.collection,
				jsonObject.get("id").toString());

		if (parentData.getDouble("status") == 1) {
			parentData.put("status", true);
		} else if (parentData.getDouble("status") == 0) {
			parentData.put("status", false);
		}

		parentData.remove("_id");

		JsonParser parser = new JsonParser();
		Assert.assertEquals(parser.parse(parentData.toString()), parser.parse(objectFromDatabase));

	}

    @Before("@US_038 or @US_018 or @Create or @Update")
    public void create_body_for_a_new_user() throws Exception {
        userRequest.setType(QARandomData.correctRangeInt(1, 2));
        userRequest.setFirstName(lorem.getFirstName());
        userRequest.setLastName(lorem.getLastName());
        userRequest.setEmail(lorem.getEmail());
        userRequest.setPassword(RSAUtil.encryptString((lorem.getFirstName() + "123456789")));
        userRequest.setStatus(QARandomData.correctRangeInt(0, 1));
        userJson = new JSONObject(userRequest);
       }

    @Given("I have the information to {string} a user with {string} {string}:")
    public void i_have_the_information_to_a_user_with(String operation,String field, String data) {
        if (operation.equals("update")) {
            if (field.equals("id")) {
                if (data.equals("null")) {
                    base.id = null;
                }
                if (data.equals("invalid")) {
                    base.id = QARandomData.randomString();
                }
            } else {
                base.id = MongoDBUtils.getRandomID(base.environment, base.uridb, "users");
            }
        }

        if (data.equals("null")) {
            userJson.put(field, (Object) null);
        }
        if (data.equals("invalid")) {
            if (field.equals("email") || field.equals("password")) {
                userJson.put(field, QARandomData.randomString());
            }
            if (field.equals("type") || field.equals("status")) {
                userJson.put(field, QARandomData.negativeInt());
            }
        }
        if (data.equals("repeated")) {
            String mongoObject;

            base.id = MongoDBUtils.getRandomID(base.environment, base.uridb, "users");
            mongoObject = MongoDBUtils.getJObjectByID(base.environment, base.uridb, "users", base.id);

            JSONObject userObject = new JSONObject(mongoObject);
            userJson.put(field, userObject.getString("email"));
        }
        base.requestBody = userJson.toString();
    }

    @Given("I want to login a user with the {string} {string}")
    public void i_Want_To_Login_A_User_With_The(String field, String data) {
        QAUtils.field=field;
        if (field.equals("status")) {
            userJson = QAUtils.getJUserByStatus(base.environment, base.uridb, "users", "0"); }
        else {
         userJson = QAUtils.getJUserByStatus(base.environment,base.uridb,"users","1");
            if (data.equals("null")) {
                userJson.put(field, (Object) null); }
            if(data.equals("invalid")){
                userJson.put(field,QARandomData.randomString()); }}

        userJson.remove("type");
        userJson.remove("status");
        userJson.remove("lastName");
        userJson.remove("firstName");
        userJson.remove("_id");
        userJson.remove("_class");


        base.requestBody = userJson.toString();


    }

    @And("I have the global error message")
    public void i_Have_The_Global_Error_Message() {

        JSONObject details;
        JSONObject failureResponse = new JSONObject(base.response.getBody());
        try{
            JSONArray detailsArray = new JSONArray(failureResponse.getJSONArray("details").toString());
            details = new JSONObject(detailsArray.get(0).toString());}
        catch(Exception e){ details=null;}
        Assert.assertTrue(QAUtils.validateRegex(QAUtils.timestampSecondFormat, failureResponse.get("timestamp").toString()));
        Assert.assertTrue("Bad Request".equals(failureResponse.get("error")) ||
                "Unauthorized".equals(failureResponse.get("error"))||
                "Not Found".equals(failureResponse.get("error")));
        Assert.assertEquals(QAUtils.expectedStatus, failureResponse.get("status").toString());
        Assert.assertEquals(base.apiResource, failureResponse.get("path"));
        Assert.assertNotNull( failureResponse.get("message"));
        if(details!=null){
            Assert.assertNotNull(details.get("fieldName"));
            Assert.assertNotNull(details.get("errorMessage"));}

    }


}
