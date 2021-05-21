@Get

Feature: Get
  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database

  @US_016 @1
  Scenario: Get all registered users in the DB with the correct information when the number of users is > 0
  Given I want to retrieve all users
  When I send a GET request
  Then the status code should be "200"
  And the number of registered users should be the same of the number of retrieved users
  And information retrieved from service should match with DB
  @US_016 @2
  Scenario: Get an empty list when there aren't registered users in the DB
    Given I want to retrieve 0 registered users
    When I send a GET request
    Then the status code should be "200"
    And the number of registered users should be 0
    And information retrieved from service should be an empty list

@US_025 @1 @Deprecated
  Scenario: Get the histories of a user
  Given I want to retrieve the histories that correspond to this id
  And I am targeting endpoint for "get_histories"
  When I send a GET request to get the histories
  Then The status code should be "200"
  And The number of registered histories should be the same of retrieved histories
  And Information retrieved from service should match with DB collection histories

@US_025 @2
Scenario: Get an empty list of histories
  Given I want to retrieve the histories that correspond to this id
  And I am targeting endpoint for "get_histories"
  When I send a GET request to get the histories
  Then The status code should be "200"
  And The number of registered histories should be 0
  And Information retrieved from get histories should be an empty list

  @US_038 @retrieve @32
  Scenario: When I try to get a user with an incorrect ID, i retrieve the global error message
    Given I want to "get" a "invalid" user
    And   I am targeting endpoint for "get_user"
    And   I am completing the path
    When  I send a GET request
    Then  The status code should be "404"
    And   I have the global error message