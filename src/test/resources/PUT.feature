@Put
Feature: send users values to updating a specific user by id

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database

  @US_020
  Scenario: User data is not changed in the database, value sent in lastName is int
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |                         2 |
      | firstName | halo                      |
      | lastName  |                           |
      | email     | ale13424@agilethought.com |
      | password  | hola1235                  |
      | status    |                         1 |
    And the value of lastName is 101
    And I am targeting endpoint for "updating_users"
    And I send a PUT request
    Then The status code of the result should be "400"
    And Information from response body should match with error "Bad Request" message

  @US_020 @7
  Scenario: User data is not changed in the database, value sent in email is int
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |        2 |
      | firstName | memana   |
      | lastName  | hola2    |
      | email     |          |
      | password  | hola1235 |
      | status    |        1 |
    And the value of email is 101
    And I am targeting endpoint for "updating_users"
    And I send a PUT request
    Then The status code of the result should be "400"
    And Information from response body should match with error "Bad Request" message

  @US_020 @8
  Scenario: User data is not changed in the database, value sent in password is int
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |                         2 |
      | firstName | gala                      |
      | lastName  | hola2                     |
      | email     | ale13424@agilethought.com |
      | password  |                           |
      | status    |                         1 |
    And the value of password is 10134
    And I am targeting endpoint for "updating_users"
    And I send a PUT request
    Then The status code of the result should be "400"
    And Information from response body should match with error "Bad Request" message

  @US_020 @9
  Scenario: User is changed in the database with number in string format in the status field
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |                         2 |
      | firstName | jorge                     |
      | lastName  | hola2                     |
      | email     | ale13424@agilethought.com |
      | password  | hola1235                  |
      | status    | memo                      |
    And I am targeting endpoint for "updating_users"
    And I send a PUT request
    Then The status code of the result should be "200"
    And Information from response body should match with DB collection "users"

  @US_020
  Scenario: User data is changed in the database, email can be null
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |        2 |
      | firstName | jorge    |
      | lastName  | hola2    |
      | email     |          |
      | password  | hola1235 |
      | status    |        1 |
    And I am targeting endpoint for "updating_users"
    And I send a PUT request
    Then The status code of the result should be "200"
    And Information from response body should match with DB collection "users"

  @US_020 @14
  Scenario: User data is changed in the database, password can be null
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |                         2 |
      | firstName | jorge                     |
      | lastName  | hola2                     |
      | email     | ale13424@agilethought.com |
      | password  |                           |
      | status    |                         1 |
    And I am targeting endpoint for "updating_users"
    And I send a PUT request
    Then The status code of the result should be "200"
    And Information from response body should match with DB collection "users"

  @US_020
  Scenario: User data is changed in the database, status can be null
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |                         2 |
      | firstName | jorge                     |
      | lastName  | hola2                     |
      | email     | ale13424@agilethought.com |
      | password  | hola1235                  |
      | status    |                         1 |
    And I am targeting endpoint for "updating_users"
    And I send a PUT request
    Then The status code of the result should be "200"
    And Information from response body should match with DB collection "users"

  @US_020 @2
  Scenario: The id for added on the url doesn't exist
    Given This id doesn't belong to any user "3251a2da0ba31a1e7ae6d31b"
    And I have the following information to update user by id and build a request body:
      | type      |                    2 |
      | firstName | Jonathan             |
      | lastName  | Lozano               |
      | email     | jon@agilethought.com |
      | password  | lozano               |
      | status    |                    1 |
    And I am targeting endpoint for "updating_users"
    When I send a PUT request
    Then The status code should be "404"
    And Information from response body should match with error "Not Found" message

  @US_020 @3
  Scenario: Some of the values didn't change when trying to update them
    Given I get the id of a new user
      | type      |                      1 |
      | firstName | Sergio                 |
      | lastName  | Ahumada                |
      | email     | mario@agilethought.com |
      | password  | c0mpU7eR312            |
      | status    |                      0 |
    And I am targeting endpoint for "updating_users"
    When I send a PUT request
    Then The status code should be "400"
    And Information from response body should match with error "Bad Request" message

@US_020 @1
  Scenario: User is update correctly
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      | 2                      |
      | firstName | Mateo                  |
      | lastName  | Galban                 |
      | email     | mateo@agilethought.com |
      | password  | g4L8An                 |
      | status    |                      1 |
    And I am targeting endpoint for "updating_users"
    When I send a PUT request
    Then The status code of the result should be "200"

  @US_020 @4
  Scenario: User data is not changed in the database, value sent is a string in the type field
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      | typeTest               |
      | firstName | Mateo                  |
      | lastName  | Galban                 |
      | email     | mateo@agilethought.com |
      | password  | g4L8An                 |
      | status    |                      1 |
    And I am targeting endpoint for "updating_users"
    When I send a PUT request
    Then The status code of the result should be "400"
    And Information from response body should match with error "Bad Request" message

  @US_020 @5
  Scenario: User data is not changed in the database,firstName has an incorrect data type
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type     |                    2 |
      | lastName | Ploneda              |
      | email    | Rob@agilethought.com |
      | password |                  123 |
      | status   |                    1 |
    And the value of firstName is 5
    And I am targeting endpoint for "updating_users"
    When I send a PUT request
    Then The status code of the result should be "400"
    And Information from response body should match with error "Bad Request" message

  @US_020 @10
  Scenario: User data is changed in the database, value sent is a null in the type field
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |                        |
      | firstName | Harry                  |
      | lastName  | Potter                 |
      | email     | harry@agilethought.com |
      | password  | w1z4rD                 |
      | status    |                      1 |
    And I am targeting endpoint for "updating_users"
    When I send a PUT request
    Then The status code of the result should be "200"
    And Information from response body should match with DB collection "users"

  @US_020 @11
  Scenario: User data is changed in the database, value sent is a null in the firstName field
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |                    2 |
      | firstName |                      |
      | lastName  | weasley              |
      | email     | ron@agilethought.com |
      | password  | w345L3y              |
      | status    |                    1 |
    And I am targeting endpoint for "updating_users"
    When I send a PUT request
    Then The status code of the result should be "200"
    And Information from response body should match with DB collection "users"

  @US_020 @12
  Scenario: User data is changed in the database, value sent is a null in the lastName field
    Given I get the id of a new user
    And I have the following information to update user by id and build a request body:
      | type      |                         2 |
      | firstName | Hermione                  |
      | lastName  |                           |
      | email     | hermione@agilethought.com |
      | password  | A73u@w32a785235           |
      | status    |                         1 |
    And I am targeting endpoint for "updating_users"
    When I send a PUT request
    Then The status code of the result should be "200"
    And Information from response body should match with DB collection "users"


  @US_038 @Update @18 @Deprecated
  Scenario: Update a user with id field having a null value
    Given I have the information to "update" a user with "id" "null":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "405"
    And   I have the global error message

  @US_038 @Update @19
  Scenario: Update a user with id field having an invalid data
    Given I have the information to "update" a user with "id" "invalid":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "404"
    And   I have the global error message

  @US_038 @Update @20
  Scenario: Update a user with firstName field having a null value
    Given I have the information to "update" a user with "firstName" "null":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @21 @Deprecated
  Scenario: Update a user with firstName field having an invalid data
    Given I have the information to "update" a user with "firstName" "invalid":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @22 @Deprecated
  Scenario: Update a user with lastName field having an invalid data
    Given I have the information to "update" a user with "lastName" "invalid":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @23
  Scenario: Update a user with lastName field having a null value
    Given I have the information to "update" a user with "lastName" "null":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @24
  Scenario: Update a user with email field having a null value
    Given I have the information to "update" a user with "email" "null":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @25 @Deprecated
  Scenario: Update a user with email field having a repeated email
    Given I have the information to "update" a user with "email" "repeated":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @26
  Scenario: Update a user with email field having an invalid data
    Given I have the information to "update" a user with "email" "invalid":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @27
  Scenario: Update a user with password field having an invalid data
    Given I have the information to "update" a user with "password" "invalid":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @28
  Scenario: Update a user with password field having a null value
    Given I have the information to "update" a user with "password" "null":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @29
  Scenario: Update a user with status field having an invalid data
    Given I have the information to "update" a user with "status" "invalid":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @30
  Scenario: Update a user with type field having an invalid data
    Given I have the information to "update" a user with "type" "invalid":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @Update @31
  Scenario: Update a user with type field having a null value
    Given I have the information to "update" a user with "type" "null":
    And   I am targeting endpoint for "updating_users"
    And I am completing the path
    When  I Send a PUT request
    Then  The status code should be "400"
    And   I have the global error message