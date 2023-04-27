Feature: Benefit offers

  Scenario: Verifying the offer benefits on homepage

    Given user is on cashwise application
    When user scrolls down to four offer benefits on homepage
    Then user should see all four options of the offer benefits
      | Convenient infographics |
      | General access          |
      | Advanced Analytics      |
      | Security                |

