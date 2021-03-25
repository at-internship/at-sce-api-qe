@Delete

Feature: Delete users from database

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database

  @US_021 @1
  Scenario: Create a delete request to remove a user from database using the id generated in database
    Given I am targeting endpoint for "create_users"
    And I have the following information for new user and build a request body:
      | type      | 2                      					|
      | firstName | kevin                  					|
      | lastName  | castillo                				|
      | email     | kevin.castillo@agilethouhgt.com         |
      | password  | pru3basus21          					|
      | status    | 0                      					|
    When I send a POST request
    Then  The status code should be "201"
    When I use a existent id
    And I send a delete request
    Then the response code should be "204"
    And The response body should be null
    
  @US_021 @2
  Scenario: Create a delete request with an id that does not exist in the database 
    Given I am targeting endpoint for "delete_users"
    When  I use a not existent id
    And I send a delete request
    Then the response code should be "404"

    

 




 