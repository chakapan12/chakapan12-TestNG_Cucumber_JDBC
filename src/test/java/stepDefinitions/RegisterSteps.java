package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.CommonMethod;

public class RegisterSteps extends CommonMethod{
	
	@When("User enter valid email and password")
	public void user_enter_valid_email_and_password() {
	    lp.regEmailInputField.sendKeys(getProperty("regEmail"));
	    lp.regPasswordInputField.sendKeys(getProperty("regPassword"));
	}

	@When("User enter invalid {string} and {string} from example table")
	public void user_enter_invalid_and_from_excel_file(String email, String password) throws InterruptedException {
	    Thread.sleep(3000);
	    lp.regEmailInputField.sendKeys(email);
	    lp.regPasswordInputField.sendKeys(password);

	}

	@When("Click register button")
	public void click_register_button() throws InterruptedException {
	    Thread.sleep(3000);
	    lp.registerButton.click();
	}
	
	@When("User enter invalid email and password from excel {int}")
	public void user_enter_invalid_email_and_password_from_excel(Integer row) {
		int index = row - 1;
		lp.regEmailInputField.sendKeys(Hook.regData.get(index).get("email").toString());
	    lp.regPasswordInputField.sendKeys(Hook.regData.get(index).get("password").toString());
	}
	@Then("Verify error message with excel {int}")
	public void verify_error_message_with_excel(Integer row) {
		int index = row - 1;
		String actualMessage = lp.getTextErrorMessage();
		String expectedMessage = Hook.regData.get(index).get("error message").toString();
		Assert.assertTrue(actualMessage.contains(expectedMessage));
	}

}
