Feature: Cashwise login

  Background: Login and log in form
    Given user is on cashwise application
    And user clicks on log in button

    Scenario Outline: Verifying the login with invalid credentials
      When user logs in "<email>" and "<password>"
      Then user should land on "<url>" page

      Examples:
        | email       | password | url                                     |
        | test        | test     | https://cashwise.us/main?showLogin=true |
        | hi@test.com | test     | https://cashwise.us/main?showLogin=true |



  Scenario Outline: Verifying the error message for negative login
    When user logs in "<email>" and "<password>"
    Then user sees error messages "<email error message>" and "<password error message>"

    Examples:
      | email          | password | email error message                | password error message                 |
      | test@gmail.com | test     |                                    | Password must be at least 6 characters |
      | test           | test123  | Please enter a valid email address |                                        |
      | test           | test     | Please enter a valid email address | Password must be at least 6 characters |
