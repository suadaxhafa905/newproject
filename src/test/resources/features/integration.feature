@Integration
Feature: API and DB integration testing

  Scenario: Validate user creation flow

    Given API client is initialized

    When I send POST request to "/api/users" with body from "src/test/resources/api/createUser.json"

    Then API status code should be 201 for test case "TC_INT_001"

    And API response should contain "testuser" for test case "TC_INT_001"

    And DB value for query "SELECT COUNT(*) FROM Users WHERE Username='testuser'" should be "1" for test case "TC_INT_001"