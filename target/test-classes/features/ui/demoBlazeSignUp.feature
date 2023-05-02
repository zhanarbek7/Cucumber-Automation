Feature: Demo Blaze Sign up

  @SignUp
  Scenario Outline: Verifying account sign up
    Given user is on demo blaze application
    And user clicks on sign up button
    When user enters credentials "<username>" and "<password>" and clicks sign up
    Then user should see alert message "<message>"

    Examples:
      | username    | password | message                                |
      | test        |          | Please fill out Username and Password. |
      |             | test     | Please fill out Username and Password. |
      |             |          | Please fill out Username and Password. |
      | test        | test     | This user already exist.               |
      | placeholder | test     | Sign up successful.                    |