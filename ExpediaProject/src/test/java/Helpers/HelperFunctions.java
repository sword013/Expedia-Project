package Helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class HelperFunctions {

	public static WebDriver startBrowser(String browser) {
		WebDriver driver;
		
		if (browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--disable-notifications");// disables popups that interrupt your code
			//co.addArguments("--headless");
			driver = new ChromeDriver(co);

		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions fo = new FirefoxOptions();
			fo.addArguments("--disable-notifications");
			fo.addArguments("--headless");
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver(fo);
			
		} else { // by default start opera
			
			System.setProperty("webdriver.opera.driver", "src/test/resources/drivers/operadriver.exe");
			OperaOptions oo = new OperaOptions();
			oo.addArguments("--disable-notifications");
			oo.addArguments("--headless");
			driver = new OperaDriver(oo);
			
		}
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		return driver;

	}

}
