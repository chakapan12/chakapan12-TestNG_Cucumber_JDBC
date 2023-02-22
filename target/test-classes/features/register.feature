@Register
Feature: Register to Automation Practice Site

  Background: 
    Given User is navigated to My Account Page

  @ValidEmail
  Scenario: Register to Automation Practice Site with valid user and password
    When User enter valid email and password
    And Click log in button
    Then user should see dashboard

  @InvalidEmail
  Scenario Outline: Register to Automation Practice Site with invalid user and password
    When User enter invalid "<email>" and "<password>" from example table
    And Click register button
    Then Verify "<error message>"

    Examples: 
      | email                    | password     | error message                         |
      | testregister31@gmail     | 123*Test*456 | Please provide a valid email address. |
      |                          | 123*Test*456 | Please provide a valid email address. |
      | testregister32@gmail.com |              | Please enter an account password.     |
      |                          |              | Please provide a valid email address. |

  @InvalidEmailWithExcel
  Scenario Outline: Register to Automation Practice Site with invalid user and password
    When User enter invalid email and password from excel <row>
    And Click register button
    Then Verify error message with excel <row>

    Examples: 
      | row |
      |   1 |
      |   2 |
      |   3 |
      |   4 |
