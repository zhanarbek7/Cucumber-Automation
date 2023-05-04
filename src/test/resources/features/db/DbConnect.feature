Feature: Connection to postgres db
  Scenario: Successful connection to db
    Given user connects to the db
    When user gets all countries data by running "select * from countries;"
    Then check if there is "Canada"
