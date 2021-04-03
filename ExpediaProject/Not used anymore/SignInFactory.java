package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInFactory {

	public SignInFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "signin-loginid")
	WebElement e_user;
	@FindBy(id = "signin-password")
	WebElement e_pwd;
	@FindBy(id = "submitButton")
	WebElement e_loginButton;
	@FindBy(id = "forgot-password-unregistered-email-label")
	WebElement e_forget;
	@FindBy(id = "account-menu-icon-title")
	WebElement e_sign1;
	@FindBy(linkText = "Sign in")
	WebElement e_sign2;
	@FindBy(xpath = "(//button[@class='uitk-button uitk-button-small uitk-button-has-text uitk-menu-icon-trigger uitk-menu-trigger'])[2]")
	WebElement userNameButton;
	@FindBy(linkText = "Sign out")
	WebElement signoutButton;

	public void clickSignin() {
		e_sign1.click();
		e_sign2.click();
	}

	public void setUser(String user) {
		e_user.sendKeys(user);
	}

	public void setPassword(String pwd) {
		e_pwd.sendKeys(pwd);
	}

	public void clearFields() {
		e_user.clear();
		e_pwd.clear();
	}

	public void clickLogin() {
		e_loginButton.click();
	}

	public void doLoginProcess(String userName, String password) {
		// clickSignin();
		clearFields();
		setUser(userName);
		setPassword(password);
		clickLogin();
	}

	public void doLogoutProcess() {
		userNameButton.click();
		signoutButton.click();
	}

	public void clicForget() {
		e_forget.click();
	}
}
