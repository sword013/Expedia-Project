package FactoryRunners;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Helpers.BaseClass;
import PageFactory.FlightSearchFactory;

public class SelectingFlightRunner extends BaseClass {

	@Test
	public void onewaySelection() throws InterruptedException {
		FlightSearchFactory fsp = new FlightSearchFactory(webDriver);
		webDriver.get(prop.getProperty("onewaySelectUrl"));
		oneWaySelect(fsp, webDriver);
		Assert.assertTrue(webDriver.getCurrentUrl().contains("Flight-Information"));
		fsp = null; // gc
	}

	public void oneWaySelect(FlightSearchFactory fsp, WebDriver webDriver) throws InterruptedException {
		fsp.selectAnyFlight(2);
		Thread.sleep(2000);
		webDriver.findElement(By.id("forcedChoiceNoThanks")).click();
		String newId = getNewWindowId(webDriver);
		webDriver.switchTo().window(newId);
	}

	/**********************************************************************************************************************************************************/

	@Test
	public void returnSelection() throws InterruptedException {
		FlightSearchFactory fsp = new FlightSearchFactory(webDriver);
		webDriver.get(prop.getProperty("returnSelectUrl"));
		returnSelect(fsp, webDriver);
		Assert.assertTrue(webDriver.getCurrentUrl().contains("Flight-Information"));
	}

	public void returnSelect(FlightSearchFactory fsp, WebDriver webDriver) throws InterruptedException {
		fsp.selectFlight();
		fsp.selectFlight();
		Thread.sleep(5000);
		try {
			webDriver.findElement(By.id("forcedChoiceNoThanks")).click();
		} catch (NoSuchElementException e) {
			// dont do anything
		}
		String newId = getNewWindowId(webDriver);
		webDriver.switchTo().window(newId);
	}

	/**********************************************************************************************************************************************************/

	@Test
	public void multiSelection() throws InterruptedException {
		FlightSearchFactory fsp = new FlightSearchFactory(webDriver);
		webDriver.get(prop.getProperty("multiSelectUrl"));
		multiSelect(fsp, webDriver);
		Assert.assertTrue(webDriver.getCurrentUrl().contains("Flight-Information"));
	}

	public void multiSelect(FlightSearchFactory fsp, WebDriver webDriver) throws InterruptedException {
		// how much ever flights you have searched you can pass here
		for (int i = 1; i <= 2; i++) {
			fsp.selectFlight();
		}
		Thread.sleep(5000);
		// webDriver.findElement(By.id("forcedChoiceNoThanks")).click();
		String newId = getNewWindowId(webDriver);
		webDriver.switchTo().window(newId);
	}

	/**********************************************************************************************************************************************************/

	public String getNewWindowId(WebDriver webDriver) {
		String parent = webDriver.getWindowHandle();
		String child = "";
		Set<String> ids = webDriver.getWindowHandles();
		Iterator<String> itr = ids.iterator();

		while (itr.hasNext()) {
			child = itr.next().toString();
			if (!child.equalsIgnoreCase(parent)) {
				break;
			} else if (child.equals(parent)) {
				webDriver.close(); // close that parent; firefox ke lie try commenting or doing something with this
									// line
			}
		}
		return child;
	}

}
