Feature: GET Clients API

  Scenario: Verifying the number of clients
    Given user hits get all cleints API "/api/myaccount/clients" "true" "1" "4"
    Then user verfies that total number of clients should be "4"