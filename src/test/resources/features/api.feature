@Ab
Feature: API testing

  Scenario: Validate API endpoint status
    Given API client is initialized
    When I send GET request to "/api-docs"
    Then API status code should be 200 for test case "TC_API_001"

  @Abc
  Scenario: Validate API POST endpoint
    Given API client is initialized
    When I send POST request to "/api/users" with body from "src/test/resources/api/createUser.json"
    Then API status code should be 201 for test case "TC_API_POST_001"
    And API response should contain "testuser" for test case "TC_API_POST_001"

  @Api
  Scenario: Validate API docs page
    Given API client is initialized
    When I send GET request to "/api-docs"
    Then API status code should be 200 for test case "TC_API_001"

  @Api
  Scenario: Validate API POST with generated user
    Given API client is initialized
    When I send POST request to "/api/users" with generated user body
    Then API status code should be 201 for test case "TC_API_POST_002"

  @Api
  Scenario: Validate API POST endpoint
    Given API client is initialized
    When I send POST request to "/api/users" with generated user body
    Then API status code should be 201 for test case "TC_API_POST_002"
    And API response should match schema "schemas/createUserSchema.json"

  @Integration
  Scenario: Validate generated user flow
    Given API client is initialized
    When I send POST request to "/api/users" with generated user body
    Then API status code should be 201 for test case "TC_INT_002"
    And Generated user should exist in database for test case "TC_INT_002"
