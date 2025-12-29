package senlenium_udemy.functional_tests.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import senlenium_udemy.functional_tests.AbstractComponents.AbstractComponent;

public class Payment extends AbstractComponent {

	WebDriver driver;

	public Payment(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='title']")
	List<WebElement> labels;
	
	@FindBy(css="[class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> dropDownCountries;
	
	@FindBy(css="a[class='btnn action__submit ng-star-inserted']")
	WebElement submit;

    
//    By inputCountry = By.xpath("//div[@class='form-group']");
	By inputCountry = By.cssSelector("[placeholder=\"Select Country\"]");
    By dropdDownList = By.cssSelector("[class='ta-item list-group-item ng-star-inserted']");
    By submitBtn = By.cssSelector("a[class='btnn action__submit ng-star-inserted']");
    
	public WebElement findLabel(String labelName) {
		WebElement label = labels.stream().filter(l -> l.getText().trim().contains(labelName)).findFirst().orElse(null);
		return label;
	}

	public void EnterField(String labelName, String value) {
		WebElement label = findLabel(labelName);
		label.findElement(By.xpath("following-sibling::input[@class='input txt']")).sendKeys(value);
	}

	public void chooseCountry(String countryName) {
        WebElement inputCountryEl = getWebElement(inputCountry);
        inputCountryEl.sendKeys("ind");
		waitUntilElementVisibile(dropdDownList);
		dropDownCountries.stream().filter(d -> d.getText().equals("India")).findFirst().ifPresent(WebElement::click);
	}
	
	public Confirmation enterPaymentDetails(String countryName) {
		chooseCountry(countryName);
		scrollPage(submitBtn);
		waitUntilElementClickable(submitBtn);
		submit.click();
		Confirmation c = new Confirmation(driver);
		return c;
	}
	

}
