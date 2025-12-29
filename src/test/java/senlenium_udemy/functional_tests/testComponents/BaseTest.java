package senlenium_udemy.functional_tests.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import senlenium_udemy.functional_tests.pageObject.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage l;

	public WebDriver initilialiser() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\senlenium_udemy\\functional_tests\\resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// prop.getProperty("browser");

		if (browserName.contains("chrome")) {	
			ChromeOptions option = new ChromeOptions();
			if(browserName.contains("Headless")) {
		        option.addArguments("--headless=new");
		        option.addArguments("--window-size=1440,990");
			}
			driver = new ChromeDriver(option);
			

			
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
			System.out.println("firefox browser");
		} else {
			System.out.println("some other browser");
		}
		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, String>> getJsonToString(String filePath) throws IOException {
		// json to string
		String jsonString = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// string to hashmap (we would need jackson databind)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonString,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
		Files.copy(src, des);
		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplicaiton() throws IOException {
		driver = initilialiser();
		l = new LandingPage(driver);
		l.goTo();
		return l;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
