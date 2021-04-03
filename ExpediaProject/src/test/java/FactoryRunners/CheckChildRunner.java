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
import PageFactory.HomePageFactory;

public class CheckChildRunner extends BaseClass {

	@Test(dataProvider = "dp")
	public void childTest(String age1, String age2) {

		webDriver.get(prop.getProperty("childCheck"));

		HomePageFactory ca3 = new HomePageFactory(webDriver);
		ca3.showOptions();
		ca3.adultCount();
		ca3.childCount();
		ca3.childAge1(age1); // 2 of 2 child age selected
		ca3.childAge2(age2);
		ca3.searchOfSearchPage();
		try {
			WebElement actualAgeError = webDriver.findElement(By.id("error-missing-child-age"));
			// age error has occured
			String expectedAgeError = "Tell us the age(s) of children travelling.";
			Assert.assertEquals(actualAgeError.getText(), expectedAgeError,
					"Expected age error message did not display");
		} catch (NoSuchElementException e) {
			// no error occured
			try {
				Assert.assertTrue(webDriver.findElement(By.id("flights-advanced-options-toggle")).getText()
						.equalsIgnoreCase("Show options"));

			} catch (NoSuchElementException e2) {
				Assert.fail("Expected to pass but age passing failed");
			}
		}
	}

	@DataProvider
	public Object[][] dp() throws IOException {
		CSVReader csv = new CSVReader(new FileReader(prop.getProperty("childCSV")));
		csv.readNext(); // to skip first row
		List<String[]> data = csv.readAll();
		System.out.println("Child csv Rows : " + data.size());
		Object childDetails[][] = new Object[data.size()][2];

		for (int i = 0; i < data.size(); i++) {
			childDetails[i][0] = data.get(i)[0];
			childDetails[i][1] = data.get(i)[1];

		}
		return childDetails;
	}

}