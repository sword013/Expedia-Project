package FactoryRunners;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import Helpers.BaseClass;
import PageFactory.HomePageFactory;

public class SearchingFlightsRunner extends BaseClass {
	HomePageFactory hpf;
	static boolean firstTime = true;
	WebElement actualSDError;
	WebElement actualMultiError;
	// error messages expected
	String expectedSDError = "Please choose a different destination from origin";
	String expectedMultiError = "The departing date must occur after the previous departing date";

	// UNIT TEST CASES
	/**
	 * @throws IOException
	 ***********************************************************************************************************************************/

	@Test(dataProvider = "searchOneWayDP", priority = 1)
	public void oneWaySearching(String from, String to, String date) throws InterruptedException, IOException {
		webDriver.get(prop.getProperty("url"));
		hpf = new HomePageFactory(webDriver);
		oneWaySearch(hpf, from, to, date);

		try {
			// webDriver.findElement(By.id("location-field-leg1-destination-input-error"));//
			// this id is the s=d message
			// error
			// means error has occured and this is what we exepect
			// Assert.fail("Error message:Same source and Destination error has occured on
			// the site");
			actualSDError = webDriver.findElement(By.id("location-field-leg1-destination-input-error"));
			Assert.assertEquals(actualSDError.getText(), expectedSDError);

		} catch (NoSuchElementException e) {
			// means s=d error is not there
			Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("search"));
			// now it loads the flights
			// System.out.println("Cheapest price :"+hpf.getCheapestPrice());
			writeCheaptestFlight(1, from, to, hpf.getCheapestPrice());

		}
		// hpf=null; //garbage collection
	}

	public void oneWaySearch(HomePageFactory hpf, String from, String to, String date) throws InterruptedException {
		hpf.flightClick();
		hpf.oneWayClick();
		hpf.typefromCity(from);
		hpf.typeToCity(to);
		hpf.date(date, 1);
		hpf.search();
	}

	/*************************************************************************************************************************************/
	@Test(priority = 2, dataProvider = "searchReturnDP")
	public void returnSearching(String from, String to, String departDate, String returnDate)
			throws InterruptedException {
		webDriver.get(prop.getProperty("url"));
		hpf = new HomePageFactory(webDriver);

		returnSearch(hpf, from, to, departDate, returnDate);
		try {
			// webDriver.findElement(By.id("location-field-leg1-destination-input-error"));//
			// this id is the s=d message
			// error
			// means error has occured and this is what we exepect
			// Assert.fail("Error message:Same source and Destination error has occured on
			// the site");
			actualSDError = webDriver.findElement(By.id("location-field-leg1-destination-input-error"));
			Assert.assertEquals(actualSDError.getText(), expectedSDError);

		} catch (NoSuchElementException e) {
			// means s=d error is not there
			Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("search"));
		}
		// hpf=null; //garbage collection

	}

	void returnSearch(HomePageFactory hpf, String from, String to, String departDate, String returnDate)
			throws InterruptedException {
		hpf.flightClick();
		hpf.returnTripClick();
		hpf.typefromCity(from);
		hpf.typeToCity(to);
		hpf.date(departDate, 1);
		hpf.date(returnDate, 2);
		hpf.search();
	}

	/*************************************************************************************************************************************/
	@Test(priority = 3, dataProvider = "searchMultiDP")
	public void multiSearching(String from1, String to1, String date1, String from2, String to2, String date2)
			throws InterruptedException {
		webDriver.get(prop.getProperty("url"));
		hpf = new HomePageFactory(webDriver);
		multiSearch(hpf, from1, to1, date1, from2, to2, date2);
		try {
			// webDriver.findElement(By.id("location-field-leg1-destination-input-error"));//
			// this id is the s=d message
			// error
			// means error has occured and this is what we exepect
			// Assert.fail("Error message:Same source and Destination error has occured on
			// the site");
			actualSDError = webDriver.findElement(By.id("location-field-leg1-destination-input-error"));
			Assert.assertEquals(actualSDError.getText(), expectedSDError);

		} catch (NoSuchElementException e) {
			// means s=d error is not there; departing error might be there now or no error
			if (webDriver.getCurrentUrl().contains("Disambiguation")) {
				// Assert.fail("Error message : The departing date must occur after the previous
				// departing date");
				try {
					actualMultiError = webDriver.findElement(By.id("Leg2DepartureDateError"));
					Assert.assertTrue(actualMultiError.getText().contains(expectedMultiError));
				} catch (NoSuchElementException e1) {
					Assert.fail("Expected Departing date error not displayed");

				}
			} else {
				// search button got clicked on; now check if it landed on search page
				Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("search"));
			}

		}

		// hpf=null; //garbage collection
	}

	void multiSearch(HomePageFactory hpf, String from1, String to1, String date1, String from2, String to2,
			String date2) throws InterruptedException {
		hpf.flightClick();
		hpf.multiCityClick();
		hpf.typefromCity(from1);
		hpf.typeToCity(to1);
		hpf.typefromCity2(from2);
		hpf.typeToCity2(to2);
		hpf.date(date1, 3);
		hpf.date(date2, 4);
		hpf.search();
	}

	/*************************************************************************************************************************************/
	// DATA PROVIDERS:

	@DataProvider
	public Object[][] searchOneWayDP() throws IOException {
		CSVReader csv = new CSVReader(new FileReader(prop.getProperty("onewaySearchCSV")));
		csv.readNext(); // to skip first row
		List<String[]> data = csv.readAll();
		System.out.println("One way csv rows" + data.size());
		Object onewaySearch[][] = new Object[data.size()][3];

		for (int i = 0; i < data.size(); i++) {
			onewaySearch[i][0] = data.get(i)[0];
			onewaySearch[i][1] = data.get(i)[1];
			onewaySearch[i][2] = data.get(i)[2];
		}
		return onewaySearch;

	}

	@DataProvider
	public Object[][] searchReturnDP() throws IOException {
		CSVReader csv = new CSVReader(new FileReader(prop.getProperty("returnSearchCSV")));
		csv.readNext(); // to skip first row
		List<String[]> data = csv.readAll();
		System.out.println("Return csv Rows : " + data.size());
		Object returnSearch[][] = new Object[data.size()][4];

		for (int i = 0; i < data.size(); i++) {
			returnSearch[i][0] = data.get(i)[0];
			returnSearch[i][1] = data.get(i)[1];
			returnSearch[i][2] = data.get(i)[2];
			returnSearch[i][3] = data.get(i)[3];
		}
		return returnSearch;

	}

	@DataProvider
	public Object[][] searchMultiDP() throws IOException {
		CSVReader csv = new CSVReader(new FileReader(prop.getProperty("multiSearchCSV")));
		csv.readNext(); // to skip first row
		List<String[]> data = csv.readAll();
		System.out.println("Multi Search csv Rows : " + data.size());
		Object multiSearch[][] = new Object[data.size()][6];

		for (int i = 0; i < data.size(); i++) {
			multiSearch[i][0] = data.get(i)[0];
			multiSearch[i][1] = data.get(i)[1];
			multiSearch[i][2] = data.get(i)[2];
			multiSearch[i][3] = data.get(i)[3];
			multiSearch[i][4] = data.get(i)[4];
			multiSearch[i][5] = data.get(i)[5];
		}
		return multiSearch;

	}

	/*************************************************************************************************************************************/
	// updated client requirements
	public void writeCheaptestFlight(int type, String from, String to, String cost) throws IOException {
		if (firstTime) {
			firstTime = false;
			CSVWriter writer = new CSVWriter(new FileWriter(prop.getProperty("cheapFlights")));
			String header[] = { "Flight Type", "From city", "To city", "Cheapest Flight", "Last checked" };
			writer.writeNext(header);
			writer.close();
		}

		CSVReader reader = new CSVReader(new FileReader(prop.getProperty("cheapFlights")));
		List<String[]> previousData = reader.readAll();
		reader.close();

		// updation
		CSVWriter writer = new CSVWriter(new FileWriter(prop.getProperty("cheapFlights")));
		writer.writeAll(previousData);

		String info[] = new String[5];

		switch (type) {
		case 1:// one way
			info[0] = "One-Way";
			break;
		case 2:// return
			info[0] = "Return";
			break;
		case 3:// multi
			info[0] = "Multi-City";
			break;
		}
		info[1] = from;
		info[2] = to;
		info[3] = cost;
		info[4] = new Date().toString();
		writer.writeNext(info);
		writer.close();

	}

}
