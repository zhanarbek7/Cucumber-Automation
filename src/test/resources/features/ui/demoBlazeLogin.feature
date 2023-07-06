Feature: Demo Blaze Login

  Scenario: Verifying the login functionality positive
    Given user is on demo blaze application
    And user clicks on log in button for demoblaze
    When user enters credentials "codewise" and "test" and clicks login
    Then user should successfully login to application