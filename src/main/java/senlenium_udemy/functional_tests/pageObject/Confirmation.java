package senlenium_udemy.functional_tests.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import senlenium_udemy.functional_tests.AbstractComponents.AbstractComponent;

public class Confirmation extends AbstractComponent {
	
	WebDriver driver;

	public Confirmation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	@FindBy(css="h1[class='hero-primary']")
//	WebElement msg;
	
	By msg = By.cssSelector("h1[class='hero-primary']");
	
	public String confirmMsg() {
		waitUntilElementVisibile(msg);
		String t = getWebElement(msg).getText().trim();
		return t;
		
	}

}
