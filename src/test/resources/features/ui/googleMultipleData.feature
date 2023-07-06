Feature: Google search

  Scenario Outline: Google search verification
    Given I am on the google page
    When I search for "<word>"
    Then I should see only "<word>" related results

    Examples:
      | word             |
      | cucumber         |
      | lexus            |
      | codewise academy |
      | Trump            |
      | Coach bag        |
