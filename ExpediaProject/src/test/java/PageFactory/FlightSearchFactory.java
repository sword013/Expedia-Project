package PageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightSearchFactory {
	public FlightSearchFactory(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this); // this class
	}

	@FindBy(xpath = "(//button[@data-test-id='select-button'])[1]")
	WebElement firstSelect;

	@FindBy(xpath = "//button[@data-test-id='select-button']")
	List<WebElement> all;

	public void selectFlight() { // 1-....
		firstSelect.click();
	}

	public void selectAnyFlight(int i) {
		all.get(i).click();
	}

}
