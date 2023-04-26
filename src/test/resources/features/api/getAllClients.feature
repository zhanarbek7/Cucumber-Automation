Feature: GET Clients API

  Scenario: Verifying the number of clients
    Given user hits get all cleints API "/api/mvacount/clients" "false" "1" "5"
    Then user verfies that total number of clients should be "3"