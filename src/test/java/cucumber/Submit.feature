@tag
Feature: Purshcase the order from ecommerce profile

Background:
Given I am on the ecommerce homepage


@Regression
Scenario Outline: Postive test of submitting an order
Given User is logged in with username <username> and password <password>
When the product is <product_name> and submit button is clicked
Then Verify the confirmation message "THANKYOU FOR THE ORDER." is present on the page


Examples:
username               | password   | product_name
testemailxyz@gmail.com | Email@com1 | ADIDAS ORIGINAL


