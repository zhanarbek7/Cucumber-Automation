Feature: Demo Blaze add to Cart feature

  Scenario Outline: Verification of product adding to Cart
    Given user is on demo blaze application
    And user clicks on the product "<product>"
    When user clicks add to cart button
    Then user sees "Product added" message on alert and clicks ok
    And user clicks on Cart button
    Then user verifies the details of the added product

    Examples:
      | product           |
      | Iphone 6 32gb     |
#      | Samsung galaxy s7 |
#      | Sony vaio i7      |