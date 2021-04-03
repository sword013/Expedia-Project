package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgetFactory {
	public ForgetFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebElement btnSearchValues, btnCancel;
	@FindBy(linkText = "Back to Sign In")
	WebElement e_cancelButton;
	@FindBy(id = "forgot-password-registered-emailId")
	WebElement e_fr;

	@FindBy(id = "forgot-password-unregistered-submit-button")
	WebElement reset_button;

	public void doReset(String user) {
		e_fr.sendKeys(user);
		reset_button.click();
	}

	public void doCancel() {
		e_cancelButton.click();
	}
}
