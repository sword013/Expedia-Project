package FactoryRunners;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import Helpers.BaseClass;
import PageFactory.CheckoutFactory;

public class CheckoutRunner extends BaseClass {
	@Test(dataProvider = "checkoutData")
	public void OneWayCheckoutTest(String title, String surname, String name, String phone, String card_no,
			String expiryMonthDay, String expiryYear, String cvv, String Country, String Billing_1, String Billing_2,
			String City, String PostCode, String Gender, String DOB_day, String DOB_month, String DOB_year,
			String email) throws InterruptedException {
		webDriver.get(prop.getProperty("onewayCheckout"));// urlCheck=https://www.expedia.co.in/Checkout/V1/FlightCheckout?tripid=0a18d5c4-9066-57ba-ad3b-435c05abe525&c=b116a691-aae4-4ae0-a4b3-b82c5ba9f2eb
		if (webDriver.getCurrentUrl().contains("CheckoutError")) {
			Assert.fail(
					"Stopper effect observed, Version has changed and as a result unit testing cannot be carried out");
		}
		CheckoutFactory cof = new CheckoutFactory(webDriver);

		if (!checkoutDetails(cof, 1, title, surname, name, phone, card_no, expiryMonthDay, expiryYear, cvv, Country,
				Billing_1, Billing_2, City, PostCode, Gender, DOB_day, DOB_month, DOB_year, email)) {
			Assert.fail("Fields have changed compared to previous version.");
		}

		// after succesful insertion and click on the submit button, now we check if
		// expected error messages arrived
		// assert
		Thread.sleep(2000);
		try {
			WebElement ageErrorActual = webDriver
					.findElement(By.xpath("(//p[@class='uitk-validation-error'][contains(text(),'18')])"));
			String expectedAgeError = "This traveller must be 18 or older.";
			// Assert.fail("This traveller must be 18 or older. ");
			Assert.assertEquals(ageErrorActual.getText(), expectedAgeError, "Expected Age error did not display");

		} catch (NoSuchElementException e) {
			// its not a age error
			try {
				WebElement actualBillingError = webDriver.findElement(
						By.xpath("(//p[@class='uitk-validation-error'][contains(text(),'valid card number.')])"));
				// billing error has occured
				// Assert.fail("enter valid card number ");
				String expectedBillingError = "Please enter a valid card number.";
				Assert.assertEquals(actualBillingError.getText(), expectedBillingError,
						"Expected Billing error did not display");
			} catch (NoSuchElementException e1) {
				// No error, proceed for payment
			}
		}

	}

	@Test(dataProvider = "checkoutData")
	public void returnCheckoutTest(String title, String surname, String name, String phone, String card_no,
			String expiryMonthDay, String expiryYear, String cvv, String Country, String Billing_1, String Billing_2,
			String City, String PostCode, String Gender, String DOB_day, String DOB_month, String DOB_year,
			String email) throws InterruptedException {
		webDriver.get(prop.getProperty("returnCheckout"));// urlCheck=https://www.expedia.co.in/Checkout/V1/FlightCheckout?tripid=0a18d5c4-9066-57ba-ad3b-435c05abe525&c=b116a691-aae4-4ae0-a4b3-b82c5ba9f2eb
		if (webDriver.getCurrentUrl().contains("CheckoutError")) {
			Assert.fail(
					"Stopper effect observed, Version has changed and as a result unit testing cannot be carried out");
		}

		CheckoutFactory cof = new CheckoutFactory(webDriver);

		if (!checkoutDetails(cof, 1, title, surname, name, phone, card_no, expiryMonthDay, expiryYear, cvv, Country,
				Billing_1, Billing_2, City, PostCode, Gender, DOB_day, DOB_month, DOB_year, email)) {
			Assert.fail("Fields have changed compared to previous version.");
		}

		// after succesful insertion and click on the submit button, now we check if
		// expected error messages arrived
		// assert
		Thread.sleep(2000);
		try {
			WebElement ageErrorActual = webDriver
					.findElement(By.xpath("(//p[@class='uitk-validation-error'][contains(text(),'18')])"));
			String expectedAgeError = "This traveller must be 18 or older.";
			// Assert.fail("This traveller must be 18 or older. ");
			Assert.assertEquals(ageErrorActual.getText(), expectedAgeError, "Expected Age error did not display");

		} catch (NoSuchElementException e) {
			// its not a age error
			try {
				WebElement actualBillingError = webDriver.findElement(
						By.xpath("(//p[@class='uitk-validation-error'][contains(text(),'valid card number.')])"));
				// billing error has occured
				// Assert.fail("enter valid card number ");
				String expectedBillingError = "Please enter a valid card number.";
				Assert.assertEquals(actualBillingError.getText(), expectedBillingError,
						"Expected Billing error did not display");
			} catch (NoSuchElementException e1) {
				// No error, proceed for payment
			}
		}

	}

	@Test(dataProvider = "checkoutData")
	public void multiCheckoutTest(String title, String surname, String name, String phone, String card_no,
			String expiryMonthDay, String expiryYear, String cvv, String Country, String Billing_1, String Billing_2,
			String City, String PostCode, String Gender, String DOB_day, String DOB_month, String DOB_year,
			String email) throws InterruptedException {
		webDriver.get(prop.getProperty("multiCheckout"));// urlCheck=https://www.expedia.co.in/Checkout/V1/FlightCheckout?tripid=0a18d5c4-9066-57ba-ad3b-435c05abe525&c=b116a691-aae4-4ae0-a4b3-b82c5ba9f2eb
		if (webDriver.getCurrentUrl().contains("CheckoutError")) {
			Assert.fail(
					"Stopper effect observed, Version has changed and as a result unit testing cannot be carried out");
		}

		CheckoutFactory cof = new CheckoutFactory(webDriver);

		if (!checkoutDetails(cof, 1, title, surname, name, phone, card_no, expiryMonthDay, expiryYear, cvv, Country,
				Billing_1, Billing_2, City, PostCode, Gender, DOB_day, DOB_month, DOB_year, email)) {
			Assert.fail("Fields have changed compared to previous version.");
		}

		// after succesful insertion and click on the submit button, now we check if
		// expected error messages arrived
		// assert
		Thread.sleep(2000);
		try {
			WebElement ageErrorActual = webDriver
					.findElement(By.xpath("(//p[@class='uitk-validation-error'][contains(text(),'18')])"));
			String expectedAgeError = "This traveller must be 18 or older.";
			// Assert.fail("This traveller must be 18 or older. ");
			Assert.assertEquals(ageErrorActual.getText(), expectedAgeError, "Expected Age error did not display");

		} catch (NoSuchElementException e) {
			// its not a age error
			try {
				WebElement actualBillingError = webDriver.findElement(
						By.xpath("(//p[@class='uitk-validation-error'][contains(text(),'valid card number.')])"));
				// billing error has occured
				// Assert.fail("enter valid card number ");
				String expectedBillingError = "Please enter a valid card number.";
				Assert.assertEquals(actualBillingError.getText(), expectedBillingError,
						"Expected Billing error did not display");
			} catch (NoSuchElementException e1) {
				// No error, proceed for payment
			}
		}

	}

	/*
	 * @Test public void CheckPageTest2() throws InterruptedException {
	 * webDriver.get(prop.getProperty("returnCheckout"));//
	 * urlCheck=https://www.expedia.co.in/Checkout/V1/FlightCheckout?tripid=0a18d5c4
	 * -9066-57ba-ad3b-435c05abe525&c=b116a691-aae4-4ae0-a4b3-b82c5ba9f2eb
	 * CheckoutFactory cof = new CheckoutFactory(webDriver); checkoutDetails(cof,
	 * 2); // assert Thread.sleep(2000); try { webDriver.findElement(By.xpath(
	 * "(//p[@class='uitk-validation-error'][contains(text(),'18')])"));
	 * Assert.fail("This traveller must be 18 or older. ");
	 * 
	 * } catch (NoSuchElementException e) { try { webDriver.findElement( By.
	 * xpath("(//p[@class='uitk-validation-error'][contains(text(),'valid card number.')])"
	 * )); Assert.fail("enter valid card number "); } catch (NoSuchElementException
	 * e1) { // cof=null; } }
	 * 
	 * }
	 * 
	 * @Test public void CheckPageTest3() throws InterruptedException {
	 * webDriver.get(prop.getProperty("multiCheckout"));//
	 * urlCheck=https://www.expedia.co.in/Checkout/V1/FlightCheckout?tripid=0a18d5c4
	 * -9066-57ba-ad3b-435c05abe525&c=b116a691-aae4-4ae0-a4b3-b82c5ba9f2eb
	 * CheckoutFactory cof = new CheckoutFactory(webDriver); checkoutDetails(cof,
	 * 3); // assert Thread.sleep(2000); try { webDriver.findElement(By.xpath(
	 * "(//p[@class='uitk-validation-error'][contains(text(),'18')])"));
	 * Assert.fail("This traveller must be 18 or older. ");
	 * 
	 * } catch (NoSuchElementException e) { try { webDriver.findElement( By.
	 * xpath("(//p[@class='uitk-validation-error'][contains(text(),'valid card number.')])"
	 * )); Assert.fail("enter valid card number "); } catch (NoSuchElementException
	 * e1) { // cof=null; } }
	 * 
	 * }
	 */
	/*
	 * @Test public void CheckPageTest3() {
	 * webDriver.get(prop.getProperty("multiCheckout")); CheckoutFactory cp = new
	 * CheckoutFactory(webDriver); cp.submit(); try { webDriver.findElement(By.
	 * xpath("//*[@id='page-level-error']//div[contains(text(),'17 errors')]"));
	 * Assert.fail("correct all 17 errors "); } catch(NoSuchElementException e2) {
	 * cp=null; } }
	 */
	public boolean checkoutDetails(CheckoutFactory cof, int i, String title, String surname, String name, String phone,
			String card_no, String expiryMonthDay, String expiryYear, String cvv, String Country, String Billing_1,
			String Billing_2, String City, String PostCode, String Gender, String DOB_day, String DOB_month,
			String DOB_year, String email) { // parameters
		boolean flag = true; // supposed to fill all details
		try {
			cof.title(title);
			cof.surname(surname);
			cof.name(name);
			cof.phoneNo(phone);
			cof.cardDetails(card_no, expiryMonthDay, expiryYear, cvv);
			cof.address(Country, Billing_1, Billing_2, City, PostCode);

			if (i == 2 || i == 3) {
				if (Gender.equals("m")) {
					cof.genderSelectMale();
				} else {
					cof.genderSelectFemale();
				}
				cof.dateOfBirth(DOB_day, DOB_month, DOB_year);
			}

			// cof.phone2("8828888888");
			// cof.emergencyContact("9788888888");

			cof.email(email);
		} catch (NoSuchElementException e) {
			flag = false; // cannot fill all details
		}

		cof.submit();
		return flag;

		/*
		 * cof.title("Mr."); cof.surname("Mathew"); cof.name("Alex");
		 * cof.phoneNo("9898989898"); cof.cardDetails("2ugj333333333", "02-Feb", "2025",
		 * "123"); cof.address("India", "Thane", "Thane", "Thane", "12345");
		 * 
		 * if (i == 2||i==3) { cof.genderSelectMale();
		 * cof.dateOfBirth("01","01-Jan","2000"); }
		 * 
		 * //cof.phone2("8828888888"); //cof.emergencyContact("9788888888");
		 * 
		 * cof.email("someone@gmail.com");
		 * 
		 * cof.submit();
		 */
	}

	@DataProvider
	public Object[][] checkoutData() throws IOException {
		CSVReader csv = new CSVReader(new FileReader(prop.getProperty("checkoutCSV")));
		csv.readNext(); // to skip first row
		csv.readNext();

		List<String[]> data = csv.readAll();
		System.out.println("Returned csv Rows : " + data.size());
		Object details[][] = new Object[data.size()][18];

		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j <= 17; j++) { // 18 columns
				details[i][j] = data.get(i)[j];
				System.out.println(details[i][j]);

			}
		}
		return details;
	}
}
