package com.at.stepdefinitions;

import com.at.globalclasses.*;
import com.at.globalclasses.domain.UserRequest;
import com.google.gson.Gson;

import gherkin.deps.com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.Map;

public class Post {
	private BasicSecurityUtil base;

	private UserRequest userRequest = new UserRequest();
	private JSONObject parentData = new JSONObject();
	private JSONObject childData = new JSONObject();

	public Post(BasicSecurityUtil base) {
		this.base = base;
	}

	@Given("I have the following information for  authenticate a user:")
	public void i_have_the_following_information_for_authenticate_a_user(Map<String, String> dataTable)
			throws Exception {
		userRequest.setEmail(dataTable.get("email"));
		userRequest.setPassword(dataTable.get("password"));
	}

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

	@When("I send a POST request")
	public void i_send_a_POST_request() {
		base.response = base.ServiceApi.POSTMethod(base.ServiceApi.hostName, base.apiResource, base.requestBody);
	}

	@Then("The status code should be {string}")
	public void the_status_code_should_be(String statusCode) {
		int status = Integer.parseInt(statusCode);
		Assert.assertEquals(status, base.ServiceApi.response.getStatusCode().value());
	}

	@Given("I have acces to the database {string}")
	public void i_have_acces_to_the_database(String database) {
		MongoDBUtils compare = new MongoDBUtils();
		String jsonFromDatabase;
		jsonFromDatabase = compare.compareJsonFromDatabase(base.environment, base.dataBase, "users",
				userRequest.getEmail());
		JsonParser parser = new JsonParser();
		Assert.assertEquals(parser.parse(jsonFromDatabase), parser.parse(base.requestBody));
	}

	@Then("There is not match with any value in DB {string}")
	public void there_is_not_match_with_any_value_in_DB(String database) {
		MongoDBUtils compare = new MongoDBUtils();
		String jsonFromDatabase;
		jsonFromDatabase = compare.compareJsonFromDatabase(base.environment, base.dataBase, "users",
				userRequest.getEmail());
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
	public void i_have_the_following_information_for_a_new_user_and_build_a_request_body(Map<String, String> userTable)
			throws Exception {
		// for null values
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
		String collectionBody = mongo.obtainObject(base.environment, base.dataBase, collection,
				base.response.getBody());
		JSONObject jsonResponse = new JSONObject(collectionBody);

		boolean bool = mongo.compareDocuments(jsonRequest, jsonResponse);
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
			String id = correctResponse.getString("id");

			MongoDBUtils userDB = new MongoDBUtils();
			JSONObject userToCompare = new JSONObject(userDB.getJObjectByID(base.environment, base.uridb, "users", id));

			Assert.assertEquals(userRequest.getEmail(), userToCompare.getString("email"));
		}
		if (body.equals("failure")) {
			JSONObject failureResponse = new JSONObject(base.ServiceApi.response.getBody());
			Assert.assertNotNull(failureResponse.get("timestamp"));
			Assert.assertEquals("Unauthorized", failureResponse.get("error"));
			Assert.assertEquals("Unauthorized", failureResponse.get("message"));
			Assert.assertEquals("/api/v1/login", failureResponse.get("path"));
		}

	}

	@Given("I want to login a user with the status {string}")
	public void i_Want_To_Login_A_User_With_The_Status(String status) throws Exception {
		MongoDBUtils randomUser = new MongoDBUtils();
		String actualStatus;
		if (status.equals("available")) {
			do {
				String id = randomUser.getRandomID(base.environment, base.uridb, "users");
				JSONObject userData = new JSONObject(
						randomUser.getJObjectByID(base.environment, base.uridb, "users", id));
				userRequest.setEmail(userData.getString("email"));
				userRequest.setPassword(userData.getString("password"));
				actualStatus = userData.get("status").toString();
			} while (!actualStatus.equals("1"));
		}
		if (status.equals("unavailable")) {
			do {
				String id = randomUser.getRandomID(base.environment, base.uridb, "users");
				JSONObject userData = new JSONObject(
						randomUser.getJObjectByID(base.environment, base.uridb, "users", id));
				userRequest.setEmail(userData.getString("email"));
				userRequest.setPassword(userData.getString("password"));
				actualStatus = userData.get("status").toString();
			} while (!actualStatus.equals("0"));

		}
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
}