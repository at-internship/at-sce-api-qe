@Post @SSO
Feature: POST for USERS

  Background: 
    Given I am working on "QA" environment
    And I am targeting "at-sso-api" service
    And I have access to "at-sso-db" database

  @US_015 @1 @Deprecated
  Scenario: Authenticate a user with a register  email and register password in database
    Given I have the following information for  authenticate a user:
      | email    | herna12312n@gmail.com |
      | password | HEernac0rt3s          |
    And I build my request body with information shown above
    And I am targeting endpoint for "authenticate_users"
    When I send a POST request to "create"
    Then The status code should be "200"
    And I have acces to the database "at-sce-db"
  @US_015 @2 @Deprecated
  Scenario: Authenticate a user with a register  email and null password in database
    Given I have the following information for  authenticate a user:
      | email    | kevin@agilethouhgt.com |
      | password |                        |
    And I build my request body with information shown above
    And I am targeting endpoint for "authenticate_users"
    When I send a POST request to "create"
    Then The status code should be "200"
    And I have acces to the database "at-sce-db"
  @US_015 @3 @Deprecated
  Scenario: Fail Authentication with a not register  email and a register password in database
    Given I have the following information for  authenticate a user:
      | email    | pruebas@gmail.com |
      | password | V4p0r30n          |
    And I build my request body with information shown above
    And I am targeting endpoint for "authenticate_users"
    When I send a POST request to "create"
    Then The status code should be "401"
    And There is not match with any value in DB "at-sce-db"
  @US_015 @4 @Deprecated
  Scenario: Fail Authentication  with a not register  email and a not register password in database
    Given I have the following information for  authenticate a user:
      | email    | pruebas@gmail.com |
      | password | pruebas           |
    And I build my request body with information shown above
    And I am targeting endpoint for "authenticate_users"
    When I send a POST request to "create"
    Then The status code should be "401"
    And There is not match with any value in DB "at-sce-db"
  @US_015 @5 @Deprecated
  Scenario: Fail Authentication  with a null email and a null password
    Given I have the following information for  authenticate a user:
      | email    |  |
      | password |  |
    And I build my request body with information shown above
    And I am targeting endpoint for "authenticate_users"
    When I send a POST request to "create"
    Then The status code should be "500"


  @US_018 @1
  Scenario: Send all correct data to create a User
    Given I have the information to "create" a user with "correct" "data":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"
  @US_018 @2
  Scenario: Send a type data in string format with all the other data in the other fields being correct
    Given I have the information to "create" a user with "type" "invalid":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_018 @3 @Deprecated
  Scenario: Send a null in the type field with all the other data in the other fields being correct
    Given I have the following information for a new user and build a request body:
      | type      |                              |
      | firstName | Migue                        |
      | lastName  | Olmos                        |
      | email     | miguelolmos@agilethought.com |
      | password  | 0lm0sM1gu3                   |
      | status    |                            1 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"
  @US_018 @4 @Deprecated
  Scenario: Send a firstName data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      |                       2 |
      | firstName |                       5 |
      | lastName  | Cernas                  |
      | email     | cer555@agilethought.com |
      | password  | Cernas5123              |
      | status    |                       0 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "400"
    And Information retrieved from Post service should match with DB collection "users"
  @US_018 @5 @Deprecated
  Scenario: Send a null in the type field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      |                       3 |
      | firstName |                         |
      | lastName  | Doriga                  |
      | email     | doriga@agilethought.com |
      | password  | doriganuNU              |
      | status    |                       0 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"
  @US_018 @6 @Deprecated
  Scenario: Send a lastName data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      |                         2 |
      | firstName | Azul                      |
      | lastName  |                        84 |
      | email     | azul8844@agilethought.com |
      | password  | azulita9898               |
      | status    |                         0 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "400"
    And Information retrieved from Post service should match with DB collection "users"
  @US_018 @7 @Deprecated
  Scenario: Send a null in the lastName field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      |                          1 |
      | firstName | Ana                        |
      | lastName  |                            |
      | email     | anamadrid@agilethought.com |
      | password  | ana88                      |
      | status    |                          1 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"
  @US_018 @8
  Scenario: Send a email data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      |           24 |
      | firstName | Manuel       |
      | lastName  | Navarro      |
      | email     |         7777 |
      | password  | n4vr00M4Nu31 |
      | status    |            1 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "400"
    And   I have the global error message
  @US_018 @9 @Deprecated
  Scenario: Send a null in the email field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      |        8 |
      | firstName | Bryan    |
      | lastName  | Garza    |
      | email     |          |
      | password  | Garza123 |
      | status    |        0 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"
  @US_018 @10
  Scenario: Send a password data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      |                       4 |
      | firstName | Ruben                   |
      | lastName  | Cuevas                  |
      | email     | cuevas@agilethought.com |
      | password  |               888779988 |
      | status    |                       1 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "400"
    And   I have the global error message
  @US_018 @11 @Deprecated
  Scenario: Send a null in the password field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      |                       1 |
      | firstName | Amelia                  |
      | lastName  | Escareno                |
      | email     | amelia@agilethought.com |
      | password  |                         |
      | status    |                       1 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"
  @US_018 @12
  Scenario: Send a status data in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      |                       6 |
      | firstName | Carlos                  |
      | lastName  | Espindola               |
      | email     | carlos@agilethought.com |
      | password  | CarEsp                  |
      | status    |                       1 |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "400"
    And   I have the global error message
  @US_018 @13 @Deprecated
  Scenario: Send a null in the status field with all the other data in the other fields being correct
    Given I have the following information for a new user and build a request body:
      | type      |                       5 |
      | firstName | Karina                  |
      | lastName  | Delgado                 |
      | email     | kardel@agilethought.com |
      | password  | K4r1N4123               |
      | status    |                         |
    And I am targeting endpoint for "create_users"
    When I send a POST request to "create"
    Then The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"

  @US_027 @US_048  @1
  Scenario: Login an existing user with the correct password and status available.
    Given I want to login a user with the "correct" "data"
    And I am targeting endpoint for "authenticate_users"
    When I send a POST request to "login"
    Then The status code should be "200"
    And   I have the "correct" body response
  @US_027 @US_048  @2
  Scenario: Login an user with the email field = null.
    Given I want to login a user with the "email" "null"
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "401"
    And   I have the "failure" body response
  @US_027 @US_048  @3
  Scenario: Login an existing user with the status available but with a wrong password.
    Given I want to login a user with the "password" "invalid"
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "401"
    And   I have the "failure" body response
  @US_027 @US_048  @4 @Deprecated
  Scenario: Login an existing user with the status available but with the password = null.
    Given I want to login a user with the "password" "null"
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "400"
    And   I have the "failure" body response
  @US_027 @US_048  @5
  Scenario: Login an existing user with the correct password but with status unavailable.
    Given I want to login a user with the "status" "invalid"
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "200"
    And   I have the "correct" body response
  @US_027 @US_048  @6 @Deprecated
  Scenario: Login a non-existing user.
    Given I want to login a user with the next information:
      | email     | thisemaildoesntexist@gmail.com |
      | password  |         12345                  |
    And   I build my request body with information shown above
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "400"
    And   I have the "failure" body response

  @US_038 @Create @1
  Scenario: Create a user with firstName field having a null value
    Given I have the information to "create" a user with "firstName" "null":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @2 @Deprecated
  Scenario: Create a user with firstName field having an invalid data
    Given I have the information to "create" a user with "firstName" "invalid":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @3 @Deprecated
  Scenario: Create a user with lastName field having an invalid data
    Given I have the information to "create" a user with "lastName" "invalid":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @4
  Scenario: Create a user with lastName field having a null value
    Given I have the information to "create" a user with "lastName" "null":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @5
  Scenario: Create a user with email field having a null value
    Given I have the information to "create" a user with "email" "null":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @6
  Scenario: Create a user with email field having a repeated email
    Given I have the information to "create" a user with "email" "repeated":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @7
  Scenario: Create a user with email field having an invalid data
    Given I have the information to "create" a user with "email" "invalid":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @8
  Scenario: Create a user with password field having an invalid data
    Given I have the information to "create" a user with "password" "invalid":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @9
  Scenario: Create a user with password field having a null value
    Given I have the information to "create" a user with "password" "null":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @10
  Scenario: Create a user with status field having an invalid data
    Given I have the information to "create" a user with "status" "invalid":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @11
  Scenario: Create a user with type field having an invalid data
    Given I have the information to "create" a user with "type" "invalid":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message
  @US_038 @Create @12
  Scenario: Create a user with type field having a null value
    Given I have the information to "create" a user with "type" "null":
    And   I am targeting endpoint for "create_users"
    When  I send a POST request to "create"
    Then  The status code should be "400"
    And   I have the global error message

  @US_038 @login @13
  Scenario: When I try to login a user with email null, I retrieve a global error message.
    Given I want to login a user with the "email" "null"
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "401"
    And   I have the "failure" body response
  @US_038 @login @14
  Scenario: When I try to login a user with email invalid, I retrieve a global error message.
    Given I want to login a user with the "email" "invalid"
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "401"
    And   I have the "failure" body response
  @US_038 @login @15
  Scenario: When I try to login a user with password invalid, I retrieve a global error message.
    Given I want to login a user with the "password" "invalid"
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "401"
    And   I have the "failure" body response
  @US_038 @login @16 @Deprecated
  Scenario: When I try to login a user with password null, I retrieve a global error message.
    Given I want to login a user with the "password" "null"
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "400"
    And   I have the "failure" body response
  @US_038 @login @17
  Scenario: When I try to login a user with status invalid, I retrieve a global error message.
    Given I want to login a user with the "status" "invalid"
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request to "login"
    Then  The status code should be "200"
    And   I have the "correct" body response

