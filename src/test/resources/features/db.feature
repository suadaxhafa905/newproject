@DB
Feature: Database validation

  Scenario: Validate user exists in database

    Then DB value for query "SELECT COUNT(*) FROM Users WHERE Username='testuser'" should be "1" for test case "TC_DB_001"