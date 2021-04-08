@Post

Feature: POST operation for authenticate an user

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database
    

  @US_015 @1
  Scenario: Authenticate a user with a register  email and register password in database
    Given I have the following information for  authenticate a user:
      | email     | ing.alexbolio@gmail.com |
      | password  | V4p0r30n                |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "200"
    And I have acces to the database "at-sce-db"

  @US_015 @2
  Scenario: Authenticate a user with a register  email and null password in database
    Given I have the following information for  authenticate a user:
      | email     | kevin@agilethouhgt.com |
      | password  |                        |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "200"
    And I have acces to the database "at-sce-db"
    
  @US_015 @3
  Scenario: Fail Authentication with a not register  email and a register password in database
    Given I have the following information for  authenticate a user:
      | email     | pruebas@gmail.com |
      | password  | V4p0r30n          |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "401"
    And   There is not match with any value in DB "at-sce-db"

  @US_015 @4
  Scenario: Fail Authentication  with a not register  email and a not register password in database
    Given I have the following information for  authenticate a user:
      | email     | pruebas@gmail.com |
      | password  | pruebas           |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "401"
		And   There is not match with any value in DB "at-sce-db"

  @US_015 @5
  Scenario: Fail Authentication  with a null email and a null password
    Given I have the following information for  authenticate a user:
      | email     |  |
      | password  |  |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "500"

  @US_018 @1
  Scenario: Send all correct data to create a User
    Given I have the following information for new user and build a request body:
      | type      | 5                      |
      | firstName | Mario                  |
      | lastName  | Ahumada                |
      | email     | mario@agilethought.com |
      | password  | computer123            |
      | status    | 1                      |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @2
  Scenario: Send a type data in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 3                     |
      | firstName | Juan                  |
      | lastName  | Lopez                 |
      | email     | juan@agilethought.com |
      | password  | JuLop98               |
      | status    | 1                     |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @3
  Scenario: Send a null in the type field with all the other data in the other fields being correct
    Given I have the following information for a new user and build a request body:
      | type      |                              |
      | firstName | Migue                        |
      | lastName  | Olmos                        |
      | email     | miguelolmos@agilethought.com |
      | password  | 0lm0sM1gu3                   |
      | status    | 1                            |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @4
  Scenario: Send a firstName data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 2                       |
      | firstName | 5                       |
      | lastName  | Cernas                  |
      | email     | cer555@agilethought.com |
      | password  | Cernas5123              |
      | status    | 0                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018  @5
  Scenario: Send a null in the type field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 3                       |
      | firstName |                         |
      | lastName  | Doriga                  |
      | email     | doriga@agilethought.com |
      | password  | doriganuNU              |
      | status    | 0                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @6
  Scenario: Send a lastName data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 2                         |
      | firstName | Azul                      |
      | lastName  | 84                        |
      | email     | azul8844@agilethought.com |
      | password  | azulita9898               |
      | status    | 0                         |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @7
  Scenario: Send a null in the lastName field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 1                          |
      | firstName | Ana                        |
      | lastName  |                            |
      | email     | anamadrid@agilethought.com |
      | password  | ana88                      |
      | status    | 1                          |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @8
  Scenario: Send a email data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 24           |
      | firstName | Manuel       |
      | lastName  | Navarro      |
      | email     | 7777         |
      | password  | n4vr00M4Nu31 |
      | status    | 1            |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @9
  Scenario: Send a null in the email field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 8        |
      | firstName | Bryan    |
      | lastName  | Garza    |
      | email     |          |
      | password  | Garza123 |
      | status    | 0        |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @10
  Scenario: Send a password data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 4                       |
      | firstName | Ruben                   |
      | lastName  | Cuevas                  |
      | email     | cuevas@agilethought.com |
      | password  | 888779988               |
      | status    | 1                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @11
  Scenario: Send a null in the password field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 1                       |
      | firstName | Amelia                  |
      | lastName  | Escareno                |
      | email     | amelia@agilethought.com |
      | password  |                         |
      | status    | 1                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @12
  Scenario: Send a status data in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 6                       |
      | firstName | Carlos                  |
      | lastName  | Espindola               |
      | email     | carlos@agilethought.com |
      | password  | CarEsp                  |
      | status    | 1                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And Information retrieved from Post service should match with DB collection "users"

  @US_018 @13
  Scenario: Send a null in the status field with all the other data in the other fields being correct
    Given I have the following information for a new user and build a request body:
      | type      | 5                       |
      | firstName | Karina                  |
      | lastName  | Delgado                 |
      | email     | kardel@agilethought.com |
      | password  | K4r1N4123               |
      | status    |                         |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And Information retrieved from Post service should match with DB collection "users"


  @US_027 @1
  Scenario: Login an existing user with the correct password and status available.
    Given I want to login a user with the status "available"
    And   I build my request body with information shown above
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "200"
    And   I have the "correct" body response

  @US_027 @2
  Scenario: Login an user with the email field = null.
    Given I want to login a user with the next information:
      | email     |                         |
      | password  |     flavio123           |
    And   I build my request body with information shown above
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "401"
    And   I have the "failure" body response

  @US_027 @3
  Scenario: Login an existing user with the status available but with a wrong password.
    Given I want to login a user with the next information:
      | email     | admin@agilethought.com  |
      | password  |  wrongpwd               |
    And   I build my request body with information shown above
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "401"
    And   I have the "failure" body response

  @US_027 @4
  Scenario: Login an existing user with the status available but with the password = null.
    Given I want to login a user with the next information:
      | email     | diana.arceo@agilethought.com |
      | password  |                              |
    And   I build my request body with information shown above
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "401"
    And   I have the "failure" body response

  @US_027 @5
  Scenario: Login an existing user with the correct password but with status unavailable.
    Given I want to login a user with the status "unavailable"
    And   I build my request body with information shown above
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "401"
    And   I have the "failure" body response

  @US_027 @6
  Scenario: Login a non-existing user.
    Given I want to login a user with the next information:
      | email     | thisemaildoesntexist@gmail.com |
      | password  |         12345                  |
    And   I build my request body with information shown above
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "401"
    And   I have the "failure" body response

  @US_030 @1
  Scenario: Create a new history with all fields filled with the correct data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "201"
    And   The history created match with the history in the data base
  @US_030 @2 @Deprecated
  Scenario: Create a new history with all fields filled with the correct data and status as null.
    Given I want to create a new history with "status" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "201"
    And   The history created match with the history in the data base
  @US_030 @3
  Scenario: Create a new history with type having a null value.
    Given I want to create a new history with "type" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @4
  Scenario: Create a new history with type having an invalid data.
    Given I want to create a new history with "type" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @5 @Deprecated
  Scenario: Create a new history with user having a null value.
    Given I want to create a new history with "user" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @6
  Scenario: Create a new history with status having an invalid data.
    Given I want to create a new history with "status" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @7
  Scenario: Create a new history with rent from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "rent" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @8
  Scenario: Create a new history with rent from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "rent" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @9
  Scenario: Create a new history with transport from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "transport" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @10
  Scenario: Create a new history with transport from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "transport" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @11
  Scenario: Create a new history with internet from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "internet" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @12
  Scenario: Create a new history with internet from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "internet" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @13
  Scenario: Create a new history with feed from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "feed" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @14
  Scenario: Create a new history with feed from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "feed" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @15
  Scenario: Create a new history with others from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "others" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @16
  Scenario: Create a new history with others from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "others" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @17
  Scenario: Create a new history with total from Fixed Expenses having a null value.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "total" "null"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @18
  Scenario: Create a new history with total from Fixed Expenses having an invalid data.
    Given I want to create a new history with "correct" "data"
    And   I have the fixed expenses with "total" "invalid"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @19
  Scenario: Create a new history with totalHours having a null value.
    Given I want to create a new history with "totalHours" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @20
  Scenario: Create a new history with totalHours having an invalid data.
    Given I want to create a new history with "totalHours" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @21
  Scenario: Create a new history with totalDays having a null value.
    Given I want to create a new history with "totalDays" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @22
  Scenario: Create a new history with totalDays having an invalid data.
    Given I want to create a new history with "totalDays" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @23
  Scenario: Create a new history with costDay having a null value.
    Given I want to create a new history with "costDay" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @24
  Scenario: Create a new history with costDay having an invalid data.
    Given I want to create a new history with "costDay" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @25
  Scenario: Create a new history with costHour having a null value.
    Given I want to create a new history with "costHour" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @26
  Scenario: Create a new history with costHour having an invalid data.
    Given I want to create a new history with "costHour" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @27
  Scenario: Create a new history with projectCost having a null value.
    Given I want to create a new history with "projectCost" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @28
  Scenario: Create a new history with projectCost having an invalid data.
    Given I want to create a new history with "projectCost" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @29
  Scenario: Create a new history with taxIVA having a null value.
    Given I want to create a new history with "taxIVA" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @30
  Scenario: Create a new history with taxIVA having an invalid data.
    Given I want to create a new history with "taxIVA" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @31
  Scenario: Create a new history with taxISR_r having a null value.
    Given I want to create a new history with "taxISR_r" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @32
  Scenario: Create a new history with taxISR_r having an invalid data.
    Given I want to create a new history with "taxISR_r" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @33
  Scenario: Create a new history with taxIVA_r having a null value.
    Given I want to create a new history with "taxIVA_r" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @34
  Scenario: Create a new history with taxIVA_r having an invalid data.
    Given I want to create a new history with "taxIVA_r" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @35
  Scenario: Create a new history with total having a null value.
    Given I want to create a new history with "total" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @36
  Scenario: Create a new history with total having an invalid data.
    Given I want to create a new history with "total" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @37
  Scenario: Create a new history with revenue having a null value.
    Given I want to create a new history with "revenue" "null"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history
  @US_030 @38
  Scenario: Create a new history with revenue having an invalid data.
    Given I want to create a new history with "revenue" "invalid"
    And   I have the fixed expenses with "correct" "data"
    And   I build my request body with the information of the history
    And   I am targeting endpoint for "create_histories"
    When  I send a POST request
    Then  The status code should be "400"
    And   I have the incorrect body response for the history

