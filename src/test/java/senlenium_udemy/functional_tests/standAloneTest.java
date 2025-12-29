package senlenium_udemy.functional_tests;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import senlenium_udemy.functional_tests.pageObject.LandingPage;
import senlenium_udemy.functional_tests.pageObject.ProductCatalogue;

public class standAloneTest {

	public void login(WebDriver driver, String email, String pswd, WebDriverWait wait) {

		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(pswd);
		driver.findElement(By.id("login")).click();

		// check if user is logged in
		String url = "https://rahulshettyacademy.com/client/#/dashboard/dash";
		wait.until(ExpectedConditions.urlMatches(url));

		Assert.assertEquals(url, driver.getCurrentUrl());
		checkout(driver, wait);
	}

	public void checkout(WebDriver driver, WebDriverWait wait) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String product = "ADIDAS ORIGINAL";
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-body")));
		
		List<WebElement> items = driver.findElements(By.className("card-body"));

		WebElement actualItem = items.stream().filter(d -> d.findElement(By.tagName("h5")).getText().trim().equals(product))
				.findFirst().orElse(null);

		actualItem.findElement(By.cssSelector(".btn.w-10.rounded")).click();

		WebElement addToCart = driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart'] label"));

		wait.until(d -> !addToCart.getText().trim().isEmpty());

		addToCart.click();

		String url = "https://rahulshettyacademy.com/client/#/dashboard/cart";
		wait.until(ExpectedConditions.urlMatches(url));

		// scroll window
		js.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[3]")));

		// verify if item is present in the page

		List<WebElement> itemsInCart = driver.findElements(By.cssSelector("[class='cartWrap ng-star-inserted']"));

		WebElement actualProduct = itemsInCart.stream()
				.filter(d -> d.findElement(By.tagName("h3")).getText().trim().contains(product)).findFirst()
				.orElse(null);

		System.out.println("product in cart is: " + actualProduct.findElement(By.tagName("h3")).getText().trim());

		Assert.assertEquals(actualProduct.findElement(By.tagName("h3")).getText().trim(), product);

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//button[contains(., 'Checkout')])")))).click();

		// call the paymentForm method
		paymentForm(driver, wait);

	}

	public void paymentForm(WebDriver driver, WebDriverWait wait) {
		

		// enter cvv in the field
		List<WebElement> labels = driver.findElements(By.xpath("//div[@class='title']"));

		WebElement cvv = labels.stream().filter(l -> l.getText().trim().contains("CVV")).findFirst().orElse(null);

		cvv.findElement(By.xpath("following-sibling::input[@class='input txt']")).sendKeys("133");

		// enter name

		WebElement name = labels.stream().filter(l -> l.getText().trim().contains("Name")).findFirst().orElse(null);

		name.findElement(By.xpath("following-sibling::input[@class='input txt']")).sendKeys("John Doe");

		// choose country from dynamic dropdown
		Actions a = new Actions(driver);
		WebElement inputCountry = driver.findElement(By.xpath("//div[@class='form-group']"));
		a.moveToElement(inputCountry).click().sendKeys("ind").build().perform();

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.cssSelector("[class='ta-item list-group-item ng-star-inserted']"))));

		List<WebElement> dropdowns = driver
				.findElements(By.cssSelector("[class='ta-item list-group-item ng-star-inserted']"));

		dropdowns.stream().filter(d -> d.getText().equals("India")).findFirst().ifPresent(WebElement::click);
		

//		Assert.assertEquals(inputCountry.getAttribute("value"), "India");
		
		// click on place order button
		driver.findElement(By.cssSelector("a[class='btnn action__submit ng-star-inserted']")).click();
		
		//check the thank you text
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[class='hero-primary']")));
		Assert.assertEquals(driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText(), "THANKYOU FOR THE ORDER.");
		
		
	}

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.manage().window().maximize();
		
		String email = "testemailxyz@gmail.com";
		String pswd = "Email@com1";
		
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));
		standAloneTest a = new standAloneTest();
//		Scanner sc = new Scanner(System.in);

		a.login(driver, email, pswd, w);
		driver.quit();
	}

}
