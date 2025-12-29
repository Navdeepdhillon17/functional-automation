package senlenium_udemy.functional_tests.pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import senlenium_udemy.functional_tests.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="card-body")
	List<WebElement> items;
	
	By findElement = By.className("card-body");
	By addProductToCart = By.cssSelector("button.btn.w-10.rounded");
	By toast = By.id("toast-container");
    By cart = By.cssSelector("button[routerlink='/dashboard/cart']"); 
	By checkOutBtn = By.xpath("(//button[contains(., 'Checkout')])");
	By spinner = By.cssSelector(".ngx-spinner-overlay");
	
	
    public List<WebElement> getProductsList(){	
    	waitUntilElementVisibile(findElement);
    	 return items;
    }
    
    public WebElement getProductByName(String productName){
    	WebElement product = getProductsList().stream().filter(d -> d.findElement(By.tagName("h5")).getText().trim().
    			equals(productName)).findFirst().orElse(null);
    
    	return product;
    }
    
    public void addProductToCart(String productName) {
    	WebElement prod = getProductByName(productName);
    	
    	prod.findElement(addProductToCart).click();
    	waitUntilElementVisibile(toast);
    	waitUntilElementInvisible(spinner);
    	WebElement cartBtn = getWebElement(cart);
    	cartBtn.click();
    }
    
    public Payment checkOut(String productName) {	
    	addProductToCart(productName);
    	waitUntilUrlContains("/dashboard/cart");
    	waitUntilElementVisibile(checkOutBtn);
    	waitUntilElementClickable(checkOutBtn);	
    	  new Actions(driver)
          .moveToElement(getWebElement(checkOutBtn))
          .pause(Duration.ofMillis(200))
          .click()
          .perform();
    	Payment pm = new Payment(driver);
    	return pm;
    }
    

}
