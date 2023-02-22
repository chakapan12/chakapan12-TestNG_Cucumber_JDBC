package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class LoginPage {

	public LoginPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}

	// Locators

	@FindBy(id = "reg_email")
	public WebElement regEmailInputField;

	@FindBy(id = "reg_password")
	public WebElement regPasswordInputField;

	@FindBy(id = "username")
	public WebElement loginUsernameInputField;

	@FindBy(id = "password")
	public WebElement loginPasswordInputField;

	@FindBy(name = "register")
	public WebElement registerButton;

	@FindBy(name = "login")
	public WebElement loginButton;

	@FindBy(xpath = "//*[@class='woocommerce-error']")
	public WebElement errorMessege;

	// Action

	public void enterUsername(String username) {
		loginUsernameInputField.sendKeys(username);
	}

	public void enterPassword(String password) {
		loginPasswordInputField.sendKeys(password);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public String getTextErrorMessage() {
		return errorMessege.getText();
	}

	public void enterRegEmail(String regEmail) {
		regEmailInputField.sendKeys(regEmail);
	}

	public void enterRegPassword(String regPassword) {
		regPasswordInputField.sendKeys(regPassword);
	}

	public void clickRegButton() {
		registerButton.click();
	}

}
