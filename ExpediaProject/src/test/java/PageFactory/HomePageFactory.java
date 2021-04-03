package PageFactory;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePageFactory {
	WebDriver webDriver;

	public HomePageFactory(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this); // this class
	}

	@FindBy(xpath = "//span[text()='Flights']")
	WebElement flight;
	@FindBy(xpath = "//span[text()='One-way']")
	WebElement oneWay;
	@FindBy(xpath = "//span[text()='Return']")
	WebElement returnTrip;
	@FindBy(xpath = "//span[text()='Multi-city']")
	WebElement multiCity;
	@FindBy(xpath = "//button[@data-testid='submit-button']")
	WebElement searchButton;

	// text fields
	@FindBy(id = "location-field-leg1-origin")
	WebElement fromCity;
	@FindBy(id = "location-field-leg1-destination")
	WebElement toCity;
	// for multi
	@FindBy(id = "location-field-leg2-origin")
	WebElement fromCity2;
	@FindBy(id = "location-field-leg2-destination")
	WebElement toCity2;

	@FindBy(xpath = "//button[@data-stid='location-field-leg1-origin-menu-trigger']")
	WebElement fromCityButton;
	@FindBy(xpath = "//button[@data-stid='location-field-leg1-destination-menu-trigger']")
	WebElement toCityButton;
	// for multi
	@FindBy(xpath = "//button[@data-stid='location-field-leg2-origin-menu-trigger']")
	WebElement fromCityButton2;
	@FindBy(xpath = "//button[@data-stid='location-field-leg2-destination-menu-trigger']")
	WebElement toCityButton2;

	// drop down results
	@FindBy(xpath = "//li[@data-stid='location-field-leg1-origin-result-item']")
	List<WebElement> fromCityDropResults;
	@FindBy(xpath = "//li[@data-stid='location-field-leg1-destination-result-item']")
	List<WebElement> toCityDropResults;
	// for multi
	@FindBy(xpath = "//li[@data-stid='location-field-leg2-origin-result-item']")
	List<WebElement> fromCityDropResults2;
	@FindBy(xpath = "//li[@data-stid='location-field-leg2-destination-result-item']")
	List<WebElement> toCityDropResults2;

	// related to date
	@FindBy(xpath = "(//*[@class='uitk-date-picker-month-name uitk-type-medium'])[1]")
	WebElement firstMonth;
	@FindBy(id = "d1-btn")
	WebElement dateButton1;
	@FindBy(id = "d2-btn")
	WebElement dateButton2;
	@FindBy(xpath = "(//*[@id='d1-btn'])[1]")
	WebElement multiDateButton1;
	@FindBy(xpath = "(//*[@id='d1-btn'])[2]")
	WebElement multiDateButton2;

	@FindBy(xpath = "//*[@data-stid='date-picker-paging'][1]")
	WebElement dateBeforeButton;
	@FindBy(xpath = "//*[@data-stid='date-picker-paging'][2]")
	WebElement dateAheadButton;
	@FindBy(xpath = "(//td/button)")
	List<WebElement> days;
	@FindBy(xpath = "//span[text()='Done']")
	WebElement doneButton;

	@FindBy(xpath = "//span[@data-test-id='listing-price-dollars']")
	List<WebElement> prices;

	// Child age:
	@FindBy(id = "flights-advanced-options-toggle")
	WebElement showOp;
	@FindBy(id = "adult-count")
	WebElement adultCount;
	@FindBy(id = "child-count")
	WebElement childCount;
	@FindBy(id = "child-age-1")
	WebElement childAge1;
	@FindBy(id = "child-age-2")
	WebElement childAge2;
	@FindBy(id = "flight-wizard-search-button")
	WebElement search;

	// functions

	public void search() {
		searchButton.click();
	}

	public void flightClick() {
		flight.click();
	}

	public void oneWayClick() {
		oneWay.click();
	}

	public void returnTripClick() {
		returnTrip.click();
	}

	public void multiCityClick() {
		multiCity.click();
	}

	public void typefromCity(String data) throws InterruptedException {
		fromCityButton.click();
		fromCity.clear();
		fromCity.sendKeys(data);
		Thread.sleep(1000);
		clickFirstResult(fromCityDropResults);
	}

	public void typefromCity2(String data) throws InterruptedException {
		fromCityButton2.click();
		fromCity2.clear();
		fromCity2.sendKeys(data);
		Thread.sleep(1000);
		clickFirstResult(fromCityDropResults2);
	}

	public void typeToCity(String data) throws InterruptedException {
		toCityButton.click();
		toCity.clear();
		toCity.sendKeys(data);
		Thread.sleep(1000);
		clickFirstResult(toCityDropResults);
	}

	public void typeToCity2(String data) throws InterruptedException {
		toCityButton2.click();
		toCity2.clear();
		toCity2.sendKeys(data);
		Thread.sleep(1000);
		clickFirstResult(toCityDropResults2);
	}

	private void clickFirstResult(List<WebElement> dropDownResults) {
		System.out.println(dropDownResults.size());
		int count = 0;
		for (WebElement w : dropDownResults) {
			System.out.println(w.getText());
			count++;
		}
		System.out.println(count);

		dropDownResults.get(0).click(); // click on the first result
	}

	public void date(String bDate, int datebutton) throws InterruptedException {
		if (datebutton == 1)
			dateButton1.click();
		else if (datebutton == 2)
			dateButton2.click();
		else if (datebutton == 3)
			multiDateButton1.click();
		else
			multiDateButton2.click();

		Thread.sleep(2000);
		System.out.println("First month is :" + firstMonth.getText());

		// logical things
		String theirMonth = firstMonth.getText();
		String a[] = theirMonth.split(" ");
		String month = a[0];
		int year = Integer.parseInt(a[1]);
		System.out.println("Month is : " + month + " Year is : " + year);

		String bookingDate = bDate;
		String b[] = bookingDate.split(" ");
		int bookDay = Integer.parseInt(b[0]);
		String bookMonth = b[1];
		int bookYear = Integer.parseInt(b[2]);

		// calculating how much to click
		if (bookYear == year) {
			// same not a prob, just calculate month difference
			int month1 = getMonthInt(month); // site month
			int month2 = getMonthInt(bookMonth); // our month
			int diff = month2 - month1;
			if (diff == 0) {
				// we can directly choose the day
				System.out.println("No need to click");
				clickDay(bookDay);
				clickDone();
			} else if (diff >= 1) {
				// we will have to click ahead by diff
				System.out.println("Click ahead " + diff + " times");
				clickAhead(diff);
				clickDay(bookDay);
				clickDone();

			} else {
				// diff <1 then we will have to click before by diff
				System.out.println("Click before " + diff + " times");
				clickBefore(Math.abs(diff));
				clickDay(bookDay);
				clickDone();
			}
		}
		if (bookYear > year) {
			// bva
			int yearDifference = bookYear - year;

			int month1 = getMonthInt(month); // site month
			int month2 = getMonthInt(bookMonth); // our month
			int monthDiff = (12 * (yearDifference) + month2) - month1;
			System.out.println("Click ahead by : " + monthDiff + " times");
			clickAhead(monthDiff);
			clickDay(bookDay);
			clickDone();
		} else {
			// will do this later; this is especially for bva
		}
	}

	public void clickAhead(int times) {
		for (int i = 1; i <= times; i++) {
			dateAheadButton.click();
		}
	}

	public void clickBefore(int times) {
		for (int i = 1; i <= times; i++) {
			dateBeforeButton.click();
		}
	}

	public void clickDone() {
		doneButton.click();
	}

	public void clickDay(int day) {
		days.get(day - 1).click();
	}

	public int getMonthInt(String month) {
		int a = 0;
		switch (month) {
		case "January":
			a = 1;
			break;
		case "February":
			a = 2;
			break;
		case "March":
			a = 3;
			break;
		case "April":
			a = 4;
			break;
		case "May":
			a = 5;
			break;
		case "June":
			a = 6;
			break;
		case "July":
			a = 7;
			break;
		case "August":
			a = 8;
			break;
		case "September":
			a = 9;
			break;
		case "October":
			a = 10;
			break;
		case "November":
			a = 11;
			break;
		case "December":
			a = 12;
			break;
		}
		return a;
	}

	public String getCheapestPrice() {
		return prices.get(0).getText();
	}

	// functions related to validations on child age
	public void showOptions() {
		showOp.click();
	}

	public void adultCount() {
		adultCount.click();
		Select sc = new Select(adultCount);
		sc.selectByVisibleText("2");

	}

	public void childCount() {
		childCount.click();
		Select sc = new Select(childCount);
		sc.selectByVisibleText("2");

	}

	public void childAge1(String age1) {
		childAge1.click();
		Select sc = new Select(childAge1);
		sc.selectByVisibleText(age1);

	}

	public void childAge2(String age2) { // passed age bcz it would choose any random value
		childAge2.click();
		Select sc = new Select(childAge2);
		sc.selectByVisibleText(age2);

	}

	public void searchOfSearchPage() {
		search.click();
	}
}
