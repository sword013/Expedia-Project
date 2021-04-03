package FactoryRunners;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.opencsv.CSVReader;

import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import Helpers.BaseClass;
import PageFactory.CheckoutFactory;
import PageFactory.FlightSearchFactory;
import PageFactory.HomePageFactory;


/*plan is to call units here
-search
  -simple search assert
-select
  -simple select assert
-info
  -simple info assert
-checkout
  -simple checkout assert
*/

public class SystemTestCasesRunner extends BaseClass {
	SearchingFlightsRunner search;
	SelectingFlightRunner select;
	CheckoutRunner checkout;
	FlightSearchFactory fsf;
	HomePageFactory hpf;
	CheckoutFactory cof;
	ExtentTest currentTc;
	String browser;
	//static ExtentReports ex;
	
	@Test(priority=1,dataProvider="oneWayDP")
	public void oneWayFlightBooking(
			String Title,String Surame,String Name,String Phone,String Debit_no,String ExpiryMonthDay,String Expiry_year,String CVV,
			String Country,String Billing_1,String Billing_2,String City,String PostCode,String Gender,String DOB_day,
			String DOB_month,String DOB_year,String email,String from,String to,String date
			) throws IOException, InterruptedException {
		//search.oneWaySearch(hpf, "Mumbai", "Delhi", "12 April 2021"); //add dp
		search.oneWaySearch(hpf,from,to,date);
		Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("search"));
		select.oneWaySelect(fsf, webDriver);
		Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("information"));
		webDriver.findElement(By.id("bookButton")).click();
		Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("checkout"));
		//checkout.checkoutDetails(cof, 1);
		if(!checkout.checkoutDetails(cof,1,Title,Surame,Name,Phone,Debit_no,ExpiryMonthDay,Expiry_year,CVV,Country,Billing_1,Billing_2,
				City,PostCode,Gender,DOB_day,DOB_month,DOB_year,email)) {
		//failed to pass all details
			Assert.fail("Fields have changed compared to previous version.");
		}
		//after this the assertion of checkout is in aftermethod
	}
	
	
	@Test(priority=2,dataProvider="returnDP")
	public void returnFlighBooking(
			String Title,String Surame,String Name,String Phone,String Debit_no,String ExpiryMonthDay,String Expiry_year,String CVV,
			String Country,String Billing_1,String Billing_2,String City,String PostCode,String Gender,String DOB_day,
			String DOB_month,String DOB_year,String email,String from,String to,String date1,String date2) throws IOException, InterruptedException {
		//search.returnSearch(hpf, "Mumbai", "Delhi", "12 April 2021", "13 April 2021");
		search.returnSearch(hpf, from, to, date1, date2);
		Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("search"));
		select.returnSelect(fsf, webDriver);
		Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("information"));
		webDriver.findElement(By.id("bookButton")).click();
		Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("checkout"));
		//checkout.checkoutDetails(cof, 2);
		if(checkout.checkoutDetails(cof, 2, Title,Surame,Name,Phone,Debit_no,ExpiryMonthDay,Expiry_year,CVV,Country,Billing_1,Billing_2,
				City,PostCode,Gender,DOB_day,DOB_month,DOB_year,email)) {
			Assert.fail("Fields have changed compared to previous version.");
		}
	}
	
	
	@Test(priority=3,dataProvider="multiDP")
	public void multiCityBooking(
			String Title,String Surame,String Name,String Phone,String Debit_no,String ExpiryMonthDay,String Expiry_year,String CVV,
			String Country,String Billing_1,String Billing_2,String City,String PostCode,String Gender,String DOB_day,
			String DOB_month,String DOB_year,String email,String from1,String to1,String date1,String from2,String to2,String date2
			) throws IOException, InterruptedException {
		//search.multiSearch(hpf, "Mumbai", "Delhi", "12 April 2021", "Delhi", "Goa", "13 April 2021");
		search.multiSearch(hpf,from1, to1, date1, from2, to2, date2);
		Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("search"));
		select.multiSelect(fsf, webDriver);
		Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("information"));
		webDriver.findElement(By.id("bookButton")).click();
		Assert.assertTrue(webDriver.getCurrentUrl().toLowerCase().contains("checkout"));
		//checkout.checkoutDetails(cof, 3);
		if(checkout.checkoutDetails(cof, 2, Title,Surame,Name,Phone,Debit_no,ExpiryMonthDay,Expiry_year,CVV,Country,Billing_1,Billing_2,
				City,PostCode,Gender,DOB_day,DOB_month,DOB_year,email)) {
			Assert.fail("Fields have changed compared to previous version.");
		}
	}
	
	
	@BeforeMethod
	public void beforeMethod() {
		search = new SearchingFlightsRunner();
		select = new SelectingFlightRunner();
		checkout = new CheckoutRunner();
		fsf = new FlightSearchFactory(webDriver);
		hpf = new HomePageFactory(webDriver);
		cof = new CheckoutFactory(webDriver);
		webDriver.get(prop.getProperty("url"));
		
	}
	
	@AfterMethod
	public void afterMethod() {
		try {
			String expectedBillingError="Please enter a valid card number.";
			WebElement billingInfoError=webDriver.findElement(By.xpath("(//p[@class='uitk-validation-error'][contains(text(),'valid card number.')])"));
			Assert.assertEquals(billingInfoError.getText(),expectedBillingError,"Appropriate error message did not display");
		} catch (NoSuchElementException e1) {
		//now pay
		}finally {
		//gc
		cof = null;
		search = null;
		select = null;
		checkout = null;
		fsf = null;
		hpf = null;
		cof = null;
		}
	}
	
	//data providers
	@DataProvider
	public Object[][] oneWayDP() throws IOException{
		CSVReader csv = new CSVReader(new FileReader(prop.getProperty("oneWayBook")));
		csv.readNext(); // to skip first row
		csv.readNext();
	
		List<String[]> data = csv.readAll();
		System.out.println("Returned onewaybook csv Rows : " + data.size());
		Object details[][] = new Object[data.size()][21];

		for (int i = 0; i < data.size(); i++) {
			for(int j=0;j<=20;j++) { //18 columns
				details[i][j] = data.get(i)[j];
				System.out.println(details[i][j]);
				
			}
		}
		return details;
	}
	
	
	@DataProvider
	public Object[][] returnDP() throws IOException{
		CSVReader csv = new CSVReader(new FileReader(prop.getProperty("returnBook")));
		csv.readNext(); // to skip first row
		csv.readNext();
	
		List<String[]> data = csv.readAll();
		System.out.println("Returned RETURN csv Rows : " + data.size());
		Object details[][] = new Object[data.size()][22]; 

		for (int i = 0; i < data.size(); i++) {
			for(int j=0;j<=21;j++) { //18 columns
				details[i][j] = data.get(i)[j];
				System.out.println(details[i][j]);
				
			}
		}
		return details;
	}
	
	@DataProvider
	public Object[][] multiDP() throws IOException{
		CSVReader csv = new CSVReader(new FileReader(prop.getProperty("multiBook")));
		csv.readNext(); // to skip first row
		csv.readNext();
	
		List<String[]> data = csv.readAll();
		System.out.println("Returned onewaybook csv Rows : " + data.size());
		Object details[][] = new Object[data.size()][24];

		for (int i = 0; i < data.size(); i++) {
			for(int j=0;j<=23;j++) { //18 columns
				details[i][j] = data.get(i)[j];
				System.out.println(details[i][j]);
				
			}
		}
		return details;
	}

}
