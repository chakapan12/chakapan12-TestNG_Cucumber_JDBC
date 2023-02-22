@Login
Feature: Log in to Automation Practice Site

  Background: 
    Given User is navigated to My Account Page

  @ValidCredentials
  Scenario: Log in to Automation Practice Site with valid user and password
    When User enter valid username and password
    And Click log in button
    Then user should see dashboard

  @InValidCredentialsWithExcelFile
  Scenario Outline: Log in to Automation Practice Site with invalid user and password
    When User enter invalid username and password from excel file <row>
    And Click log in button
    Then Verify error message with excel file <row>

    Examples: 
      | row |
      |   1 |
      |   2 |
      |   3 |
      |   4 |

  @InValidCredentialsWithExampleTable
  Scenario Outline: Log in to Automation Practice Site with invalid user and password
    When User enter invalid "<username>" and "<password>"
    And Click log in button
    Then Verify "<error message>"

    Examples: 
      | username            | password     | error message                                      |
      | harry.pot@gmail.com | 123*Test*456 | A user could not be found with this email address. |
      |                     | 123*Test*456 | Username is required.                              |
