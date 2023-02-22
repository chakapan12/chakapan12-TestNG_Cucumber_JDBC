package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.BaseClass;
import utilities.PageInitializer;

public class LoginSteps extends PageInitializer {
	
	Logger log = LogManager.getLogger(LoginSteps.class);

	@Given("User is navigated to My Account Page")
	public void user_is_navigated_to_my_account_page() {
		log.info("Navigate to my account page");
//		System.out.println("Thread ID - " + Thread.currentThread().getId());
        BaseClass.getDriver().get("https://practice.automationtesting.in/my-account/");
	}

	@When("User enter valid username and password")
	public void user_enter_valid_and() {
		log.info("Enter user and password");
		lp.enterUsername(getProperty("username"));
		lp.enterPassword(getProperty("password"));
	}

	@And("Click log in button")
	public void click_log_in_button() {
		log.info("Click log in button");
		lp.clickLogin();
	}

	@Then("user should see dashboard")
	public void user_should_see_dashboard() {

	}

	@When("User enter invalid username and password from excel file {int}")
	public void user_enter_invalid_username_and_password_from_excel_file(Integer int1) {
		log.info("Enter user and password");
		int index = int1 - 1;
		lp.enterUsername(Hook.logInData.get(index).get("username").toString());
		lp.enterPassword(Hook.logInData.get(index).get("password").toString());
	}

	@Then("Verify error message with excel file {int}")
	public void verify_error_message_with_excel_file(Integer int1) {
		log.info("Verify error message");
		int index = int1 - 1;
		String actualMessage = lp.getTextErrorMessage();
		String expectedMessage = Hook.logInData.get(index).get("error message").toString();
		Assert.assertTrue(actualMessage.contains(expectedMessage));

	}

	@When("User enter invalid {string} and {string}")
	public void user_enter_invalid_and(String username, String password) {

		lp.enterUsername(username);
		lp.enterPassword(password);
	}

	@Then("Verify {string}")
	public void verify(String errorMessage) {

		String actualMessage = lp.getTextErrorMessage();
		Assert.assertTrue(actualMessage.contains(errorMessage));
	}
}
