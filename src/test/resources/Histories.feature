@Histories  @SCE
Feature: POST for Histories
  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sso-api" service
    And I have access to "at-sso-db" database

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
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When I send a GET request to get the histories
    Then The status code should be "401"
    And The number of registered histories should be 0

  @US_030 @1
  Scenario: Create a new history with all fields filled with the correct data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "201"
    And   The history created match with the history in the data base
  @US_030 @2 @Deprecated
  Scenario: Create a new history with all fields filled with the correct data and status as null.
    Given I want to create a new history with "status" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "201"
    And   The history created match with the history in the data base
  @US_030 @3
  Scenario: Create a new history with type having a null value.
    Given I want to create a new history with "type" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @4
  Scenario: Create a new history with type having an invalid data.
    Given I want to create a new history with "type" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @5 @Deprecated
  Scenario: Create a new history with user having a null value.
    Given I want to create a new history with "user" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @6
  Scenario: Create a new history with status having an invalid data.
    Given I want to create a new history with "status" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @7
  Scenario: Create a new history with rent from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "rent" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @8
  Scenario: Create a new history with rent from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "rent" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @9
  Scenario: Create a new history with transport from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "transport" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @10
  Scenario: Create a new history with transport from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "transport" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @11
  Scenario: Create a new history with internet from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "internet" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @12
  Scenario: Create a new history with internet from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "internet" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @13
  Scenario: Create a new history with feed from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "feed" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @14
  Scenario: Create a new history with feed from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "feed" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @15
  Scenario: Create a new history with others from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "others" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @16
  Scenario: Create a new history with others from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "others" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @17
  Scenario: Create a new history with total from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "total" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @18
  Scenario: Create a new history with total from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "total" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @19
  Scenario: Create a new history with totalHours having a null value.
    Given I want to create a new history with "totalHours" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @20
  Scenario: Create a new history with totalHours having an invalid data.
    Given I want to create a new history with "totalHours" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @21
  Scenario: Create a new history with totalDays having a null value.
    Given I want to create a new history with "totalDays" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @22
  Scenario: Create a new history with totalDays having an invalid data.
    Given I want to create a new history with "totalDays" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @23
  Scenario: Create a new history with costDay having a null value.
    Given I want to create a new history with "costDay" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @24
  Scenario: Create a new history with costDay having an invalid data.
    Given I want to create a new history with "costDay" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @25
  Scenario: Create a new history with costHour having a null value.
    Given I want to create a new history with "costHour" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @26
  Scenario: Create a new history with costHour having an invalid data.
    Given I want to create a new history with "costHour" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @27
  Scenario: Create a new history with projectCost having a null value.
    Given I want to create a new history with "projectCost" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @28
  Scenario: Create a new history with projectCost having an invalid data.
    Given I want to create a new history with "projectCost" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @29
  Scenario: Create a new history with taxIVA having a null value.
    Given I want to create a new history with "taxIVA" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @30
  Scenario: Create a new history with taxIVA having an invalid data.
    Given I want to create a new history with "taxIVA" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @31
  Scenario: Create a new history with taxISR_r having a null value.
    Given I want to create a new history with "taxISR_r" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @32
  Scenario: Create a new history with taxISR_r having an invalid data.
    Given I want to create a new history with "taxISR_r" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @33
  Scenario: Create a new history with taxIVA_r having a null value.
    Given I want to create a new history with "taxIVA_r" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @34
  Scenario: Create a new history with taxIVA_r having an invalid data.
    Given I want to create a new history with "taxIVA_r" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @35
  Scenario: Create a new history with total having a null value.
    Given I want to create a new history with "total" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @36
  Scenario: Create a new history with total having an invalid data.
    Given I want to create a new history with "total" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @37
  Scenario: Create a new history with revenue having a null value.
    Given I want to create a new history with "revenue" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
  @US_030 @38 @Deprecated
  Scenario: Create a new history with revenue having an invalid data.
    Given I want to create a new history with "revenue" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And I have the global error message
      | email    | thisemaildoesntexist@gmail.com |
      | password |                          12345 |
    And I build my request body with information shown above
    And I am targeting endpoint for "authenticate_users"
    When I send a POST request to "histories"
    Then The status code should be "401"
    And I have the "failure" body response


  @US_026 @1 @Deprecated
  Scenario: Create a history with an id provided by the user
    Given I have the following parent information for  create  a new history:
      | _id         |     123 |
      | type        |       1 |
      | user_id     |       2 |
      | totalHours  |     480 |
      | totalDays   |      90 |
      | costDay     |    1500 |
      | costHour    |     150 |
      | projectCost |  100000 |
      | taxIVA      |    4500 |
      | taxISR_r    |     234 |
      | taxIVA_r    |     122 |
      | total       |  190000 |
      | revenue     | 1000000 |
      | status      |       0 |
    And I have the following fixed expenses for  create  a new history:
      | rent      |     8000 |
      | transport |   500.50 |
      | internet  |   300.00 |
      | feed      |  1500.45 |
      | others    |      500 |
      | total     | 10800.95 |
    And I create a request body with the above information
    And I am targeting endpoint for "create_history"
    When I send a POST request to "histories"
    Then The status code should be "201"
    And Information retrieved from Post operation should match with the collection "histories"
  @US_026 @2 @Deprecated
  Scenario: Create a history with a null id
    Given I have the following parent information for  create  a new history:
      | type        |        2 |
      | user_id     |      100 |
      | totalHours  |       40 |
      | totalDays   |        5 |
      | costDay     |      150 |
      | costHour    |       15 |
      | projectCost |   200000 |
      | taxIVA      |     2000 |
      | taxISR_r    |      350 |
      | taxIVA_r    |      200 |
      | total       | 10002020 |
      | revenue     |  1000000 |
      | status      |        1 |
    And I have the following fixed expenses for  create  a new history:
      | rent      |  350 |
      | transport |    0 |
      | internet  |  500 |
      | feed      | 4000 |
      | others    | 2500 |
      | total     | 7350 |
    And I create a request body with the above information
    And I am targeting endpoint for "create_history"
    When I send a POST request to "histories"
    Then The status code should be "201"
    And Information retrieved from Post operation should match with the collection "histories"

  @US_038 @35 @histories
  Scenario: Create a new history with type having a null value.
    Given I want to create a new history with "type" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @36 @histories
  Scenario: Create a new history with type having an invalid data.
    Given I want to create a new history with "type" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @37 @histories @Deprecated
  Scenario: Create a new history with user having a null value.
    Given I want to create a new history with "user" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @38 @histories
  Scenario: Create a new history with status having an invalid data.
    Given I want to create a new history with "status" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @39 @histories
  Scenario: Create a new history with rent from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "rent" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @40 @histories
  Scenario: Create a new history with rent from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "rent" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @41 @histories
  Scenario: Create a new history with transport from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "transport" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @42 @histories
  Scenario: Create a new history with transport from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "transport" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @43 @histories
  Scenario: Create a new history with internet from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "internet" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @44 @histories
  Scenario: Create a new history with internet from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "internet" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @45 @histories
  Scenario: Create a new history with feed from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "feed" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @46 @histories
  Scenario: Create a new history with feed from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "feed" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @47 @histories
  Scenario: Create a new history with others from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "others" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @48 @histories
  Scenario: Create a new history with others from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "others" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @49 @histories
  Scenario: Create a new history with total from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "total" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @50 @histories
  Scenario: Create a new history with total from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "total" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @51 @histories
  Scenario: Create a new history with totalHours having a null value.
    Given I want to create a new history with "totalHours" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @52 @histories
  Scenario: Create a new history with totalHours having an invalid data.
    Given I want to create a new history with "totalHours" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @53 @histories
  Scenario: Create a new history with totalDays having a null value.
    Given I want to create a new history with "totalDays" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @54 @histories
  Scenario: Create a new history with totalDays having an invalid data.
    Given I want to create a new history with "totalDays" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @55 @histories
  Scenario: Create a new history with costDay having a null value.
    Given I want to create a new history with "costDay" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @56 @histories
  Scenario: Create a new history with costDay having an invalid data.
    Given I want to create a new history with "costDay" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @57 @histories
  Scenario: Create a new history with costHour having a null value.
    Given I want to create a new history with "costHour" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @58 @histories
  Scenario: Create a new history with costHour having an invalid data.
    Given I want to create a new history with "costHour" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @59 @histories
  Scenario: Create a new history with projectCost having a null value.
    Given I want to create a new history with "projectCost" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @60 @histories
  Scenario: Create a new history with projectCost having an invalid data.
    Given I want to create a new history with "projectCost" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @61 @histories
  Scenario: Create a new history with taxIVA having a null value.
    Given I want to create a new history with "taxIVA" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @62 @histories
  Scenario: Create a new history with taxIVA having an invalid data.
    Given I want to create a new history with "taxIVA" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @63 @histories
  Scenario: Create a new history with taxISR_r having a null value.
    Given I want to create a new history with "taxISR_r" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @64 @histories
  Scenario: Create a new history with taxISR_r having an invalid data.
    Given I want to create a new history with "taxISR_r" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @65 @histories
  Scenario: Create a new history with taxIVA_r having a null value.
    Given I want to create a new history with "taxIVA_r" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @66 @histories
  Scenario: Create a new history with taxIVA_r having an invalid data.
    Given I want to create a new history with "taxIVA_r" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @67 @histories
  Scenario: Create a new history with total having a null value.
    Given I want to create a new history with "total" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @68 @histories
  Scenario: Create a new history with total having an invalid data.
    Given I want to create a new history with "total" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @69 @histories
  Scenario: Create a new history with revenue having a null value.
    Given I want to create a new history with "revenue" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @70 @histories
  Scenario: Create a new history with revenue having an invalid data.
    Given I want to create a new history with "revenue" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    When  I send a POST request to "histories"
    Then  The status code should be "400"
    And   I have the global error message