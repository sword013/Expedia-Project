package Helpers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class BaseClass {
	public WebDriver webDriver;
	public Properties prop;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream("settings.property"));
		// webDriver = HelperFunctions.startBrowser(prop.getProperty("browserName"));
		webDriver = HelperFunctions.startBrowser(browser);
		webDriver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitWaitTime")),
				TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(2000);
		webDriver.quit();
	}

}
