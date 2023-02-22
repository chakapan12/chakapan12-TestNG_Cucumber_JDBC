# TestNG_Cucumber_JDBC

This is hybrid framework (Data Driven Development and Behavior Driven Development) using testNG and cucumber.

Test data is separate from test scripts which imports from excel files, example table in feature files and properties files

Using Gherken laguage to write scenarios make them easy understand all the test steps even non technical people.

Implement Page Object Model with Page Factory to create webelement repositories and separate them from test scripts.

Connecting to mySQL database using JDBC.

--> Creating method to format and print result set on console by finding max width of each column then plus 3 character. 

--> Converting result set to list of map. 

--> Updating and inserting database with java faker. 

--> Allowing to do database testing. 

--> Creating email, password and generate token. Then insert to database. 

--> Performing login testing by executing sql query to get all login_info and storing into list of map. Then generate token from input email and password to validate with database.
