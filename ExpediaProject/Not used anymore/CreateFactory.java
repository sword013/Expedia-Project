package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateFactory {
	public CreateFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "create-account-firstname")
	WebElement e_fname;
	@FindBy(id = "create-account-lastname")
	WebElement e_lname;
	@FindBy(id = "create-account-emailId")
	WebElement e_mail;
	@FindBy(id = "create-account-password")
	WebElement e_pwd;
	@FindBy(id = "create-account-confirm-password")
	WebElement e_cpwd;
	@FindBy(id = "create-account-submit-button")
	WebElement e_create;

	public void setFname(String fname) {
		e_fname.sendKeys(fname);
	}

	public void setLname(String lname) {
		e_lname.sendKeys(lname);
	}

	public void setEmail(String email) {
		e_mail.sendKeys(email);
	}

	public void setPassword(String pwd) {
		e_pwd.sendKeys(pwd);
	}

	public void setCpwd(String cpwd) {
		e_cpwd.sendKeys(cpwd);
	}

	public void clearFields() {
		e_fname.clear();
		e_lname.clear();
		e_mail.clear();
		e_pwd.clear();
		e_cpwd.clear();

	}

	public void clickSignUp() {
		e_create.click();
	}

	public void doSignUpProcess(String fname, String lname, String email, String pwd, String cpwd) {
		// clickSignin();
		clearFields();
		setFname(fname);
		setLname(lname);
		setEmail(email);
		setPassword(pwd);
		setCpwd(cpwd);
		clickSignUp();
	}

}
