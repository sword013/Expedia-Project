package FactoryRunners;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.opencsv.CSVReader;

import Helpers.BaseClass;

public class NonFunctional extends BaseClass{
	
	@Test(dataProvider="dp")
	public void buttonColorTest(WebElement button,String expectedColorHex,String property) {
		String actualColor,actualColorHex;
		
		if(property.equals("background-color")) {
			actualColor = button.getCssValue("background-color"); //rgb
			actualColorHex = Color.fromString(actualColor).asHex();
			Assert.assertEquals(actualColorHex,expectedColorHex,"Background Color did not match");
			
		}else if(property.equals("color")) {
			actualColor = button.getCssValue("color"); //rgb
			actualColorHex = Color.fromString(actualColor).asHex();
			Assert.assertEquals(actualColorHex,expectedColorHex,"Font Color did not match");
		}
	}
	
	@DataProvider
	public Object[][] dp() throws IOException {
		CSVReader csv = new CSVReader(new FileReader(prop.getProperty("colorCSV")));
		csv.readNext(); // to skip first row
		webDriver.get(prop.getProperty("url"));
		List<String[]> data = csv.readAll();
		System.out.println("Returned color csv Rows : " + data.size());
		Object details[][] = new Object[data.size()][3]; 
		String path;
		for (int i = 0; i < data.size(); i++) {
			path="//*[text()='"+data.get(i)[0]+"']";
			details[i][0]= webDriver.findElement(By.xpath(path));
			details[i][1]= data.get(i)[1];
			details[i][2]= data.get(i)[2];
		}
		return details;
	}
	
	
}
