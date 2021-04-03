package PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckoutFactory {
	public CheckoutFactory(WebDriver driver) {
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="title[0]") WebElement title;
	@FindBy(id="lastname[0]") WebElement surname;
	@FindBy(id="firstname[0]") WebElement firstName;
	@FindBy(id="FLT.CKO.Phone.CountryCode") WebElement countryCode;
	@FindBy(id="phone-number[0]") WebElement phoneNo;
	@FindBy(id="gender_female[0]") WebElement genderFemale;
	@FindBy(id="gender_male[0]") WebElement genderMale; //
	@FindBy(id="date_of_birth_day[0]") WebElement day;
	@FindBy(id="date_of_birth_month0") WebElement month;
	@FindBy(id="date_of_birth_year[0]") WebElement year;
	@FindBy(id="creditCardInput") WebElement cardNo;
	@FindBy(name="expiration_month") WebElement expiryMonth;
	@FindBy(name="expiration_year") WebElement expiryYear;
	@FindBy(id="new_cc_security_code") WebElement sCode;
	@FindBy(name="country") WebElement cntry;
	@FindBy(name="street") WebElement s;
	@FindBy(name="street2") WebElement s1;
	@FindBy(name="city") WebElement cty;
	@FindBy(name="zipcode") WebElement zip;
	@FindBy(id="complete-booking")WebElement submit;
	
	//new
	@FindBy(name="tripPreferencesRequest.airTripPreferencesRequest.travelerPreferences[0].emergencyContactName") WebElement emergencyContact;
	@FindBy(id = "emergency-phone-number[0]") WebElement phone2;
	@FindBy(xpath="(//*[@name='email'])[2]") WebElement emails;
	
	public void title(String x) {
		Select sc = new Select(title);
		sc.selectByVisibleText(x);
	}
	public void surname(String x) {
		surname.sendKeys(x);
	}
	public void name(String x) {
		firstName.sendKeys(x);
	}
	public void CountryCode(String x) {
		Select sc = new Select(countryCode);
		sc.selectByVisibleText(x);
	}
	public void phoneNo(String x) {
		phoneNo.sendKeys(x);
	}
	public void genderSelectMale() {
		genderMale.click();
		
	}
	public void genderSelectFemale() {
		genderFemale.click();
		
	}
	public void dateOfBirth(String Day,String Month,String Year) {
		Select sc = new Select(day);
		sc.selectByVisibleText(Day);
		Select sc1 = new Select(month);
		sc1.selectByVisibleText(Month);
		Select sc2= new Select(year);
		sc2.selectByVisibleText(Year);
	}
	public void cardDetails(String cardNumber,String expMonth,String expYear,String securityCode ) {
		cardNo.sendKeys(cardNumber);
		Select sc = new Select(expiryMonth);
		sc.selectByVisibleText(expMonth);
		Select sc1 = new Select(expiryYear);
		sc1.selectByVisibleText(expYear);
		sCode.sendKeys(securityCode);
		
		
	}
	public void address(String country,String address1,String address2,String city,String zipcode) {
		Select sc = new Select(cntry);
		sc.selectByVisibleText(country);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.sendKeys(address1);
		s1.sendKeys(address2);
		cty.sendKeys(city);
		zip.sendKeys(zipcode);
		
		
	}
	public void submit() {
		submit.click();
	}
	
	//new
	public void emergencyContact(String contact) {
		emergencyContact.sendKeys(contact);
	}
	public void phone2(String contact) {
		phone2.sendKeys(contact);
	}
	
	public void email(String e) {
		emails.sendKeys(e);
	}
	
}
