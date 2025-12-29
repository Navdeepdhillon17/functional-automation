package senlenium_udemy.functional_tests.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import senlenium_udemy.functional_tests.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
	    // initialisation code
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	
	public ProductCatalogue login(String email, String pswd) {
		userEmail.sendKeys(email);
		password.sendKeys(pswd);
		submit.click();
		ProductCatalogue p = new ProductCatalogue(driver);
		return p; 
		
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	

}
