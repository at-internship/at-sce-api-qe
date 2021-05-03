package com.at.stepdefinitions;

import com.at.globalclasses.*;
import com.at.globalclasses.domain.UserRequest;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.cucumber.java.en.And;

import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.context.weaving.DefaultContextLoadTimeWeaver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLOutput;
import java.util.Map;

public class Delete {
	private BasicSecurityUtil base;
	private String id;

	private UserRequest userRequest = new UserRequest();

	public Delete(BasicSecurityUtil base) {
		this.base = base;
	}

	@Given("I have the following information for create a new user and build a request body:")
	public void i_have_the_following_information_for_create_a_new_user_and_build_a_request_body(
			Map<String, String> userTable) throws Exception {
		userRequest.setType(Integer.parseInt(userTable.get("type")));
		userRequest.setFirstName(userTable.get("firstName"));
		userRequest.setLastName(userTable.get("lastName"));
		userRequest.setEmail(userTable.get("email"));
		userRequest.setPassword(userTable.get("password"));
		userRequest.setStatus(Integer.parseInt(userTable.get("status")));

		Gson gson = new Gson();
		base.requestBody = gson.toJson(userRequest);
	}

	@When("I use a existent id")
	public void i_use_a_existent_id() {
		String idRequestBody = base.ServiceApi.response.getBody();
		JSONObject idRequestBodyJson = new JSONObject(idRequestBody);
		id = idRequestBodyJson.get("id").toString();
	}

	@When("I use a not existent id")
	public void i_use_a_not_existent_id() {
		Faker faker = new Faker();
		id = faker.regexify("([0-9]){12}([a-f]){1}([0-9]){2}([a-f]){1}([0-9]){2}([a-f]){1}([0-9]){5}");
	}

	@When("I send a delete request")
	public void i_send_a_delete_request() {
		base.response = base.ServiceApi.retrieveDelete(base.ServiceApi.hostName, base.apiResource + "/" + id);
	}

	@Then("the response code should be {string}")
	public void the_response_code_should_be(String statusCode) {
		int status = Integer.parseInt(statusCode);
		QAUtils.expectedStatus=statusCode;
		Assert.assertEquals(status, base.ServiceApi.response.getStatusCode().value());
	}

	@Then("The response body should be null")
	public void the_response_body_should_be_null() {
		Assert.assertEquals(null, base.response.getBody());
	}

	@And("I send a DELETE request")
	public void i_Send_A_DELETE_Request() {
		System.out.println("HostName:"+base.ServiceApi.hostName );
		base.response = base.ServiceApi.retrieveDelete(base.ServiceApi.hostName, base.apiResource);
	}
}
