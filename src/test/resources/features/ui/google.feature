Feature: Google search
  #UserStory: SUP-93

  Scenario: Verifying the results for google search
    Given I am on the google page
    When I search for "Trump"
    Then I should see only "Trump" related results

    Scenario: Verifying image result
      Given I am on the google page
      When I search for "James Bond"
      And I click on image option
      Then I should see only "James Bond" related images


      @nothing
      Scenario: TEst
        Given testing this with
        |hello|
        |hi   |
        |what |
        |ok   |
        Then do nothing
