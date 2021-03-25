package com.at.stepdefinitions;


import com.at.globalclasses.*;
import com.github.javafaker.Faker;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;




public class Delete {
    private BasicSecurityUtil base;
    private String id;


    public Delete(BasicSecurityUtil base) {
        this.base = base;
    }


    @When("I use a existent id")
    public void i_use_a_existent_id() {
        MongoDBUtils compare =  new MongoDBUtils();
        id = compare.getIdFromCollection(base.environment, base.dataBase, "users",Post.jsonBodyRequest.get("email").toString());
    }

    @When("I use a not existent id")
    public void i_use_a_not_existent_id() {
        Faker faker = new Faker();
        id = faker.regexify("([0-9]){12}([a-f]){1}([0-9]){2}([a-f]){1}([0-9]){2}([a-f]){1}([0-9]){5}");
    }

    @When("I send a delete request")
    public void i_send_a_delete_request() {
        base.response = base.ServiceApi.retrieveDelete(base.ServiceApi.hostName, base.apiResource+"/"+id);
    }

    @Then("the response code should be {string}")
    public void the_response_code_should_be(String statusCode) {
        int status = Integer.parseInt(statusCode);
        Assert.assertEquals(status, base.ServiceApi.response.getStatusCode().value());
    }

    @Then("The response body should be null")
    public void the_response_body_should_be_null() {
        Assert.assertEquals(null,base.response.getBody());
    }

}