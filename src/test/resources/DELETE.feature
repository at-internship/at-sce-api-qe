@Delete

Feature: Delete

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database

  @US_021 @US_051 @1
  Scenario: Create a delete request to remove a user from database using the id generated in database
    Given I am targeting endpoint for "create_users"
    And I have the following information for create a new user and build a request body:
      | type      | 1                      					|
      | firstName | kevin                  					|
      | lastName  | castillo                				|
      | email     | kevin.castillo@agilethouhgt.com         |
      | password  | CAstill0ro3s          					|
      | status    | 0                      					|
    When I send a POST request
    Then  The status code should be "201"
    When I use a existent id
    And I have access to "at-sso-db" database
    And I send a delete request
    Then the response code should be "204"
    And The response body should be null
    
  @US_021 @US_051 @2
  Scenario: Create a delete request with an id that does not exist in the database 
    Given I am targeting endpoint for "delete_users"
    And I am targeting "at-sso-api" service
    And I have access to "at-sso-db" database
    When  I use a not existent id
    And I send a delete request
    Then the response code should be "401"


  @US_038 @deleting @33 @Deprecated
  Scenario: When I try to delete a user with a null ID, i retrieve the global error message
    Given I want to "delete" a "null" user
    And   I am targeting endpoint for "delete_users"
    And   I am completing the path
    When  I send a DELETE request
    Then  The status code should be "405"
    And   I have the global error message

  @US_038 @deleting @34
  Scenario: When I try to delete a user with an incorrect ID, i retrieve the global error message
    Given I want to "delete" a "invalid" user
    And   I am targeting endpoint for "delete_users"
    And   I am completing the path
    When  I send a DELETE request
    Then  The status code should be "404"
    And   I have the global error message

 




 