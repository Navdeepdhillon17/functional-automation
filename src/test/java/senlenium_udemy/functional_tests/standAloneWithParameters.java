package senlenium_udemy.functional_tests;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import senlenium_udemy.functional_tests.pageObject.Confirmation;
import senlenium_udemy.functional_tests.pageObject.Payment;
import senlenium_udemy.functional_tests.pageObject.ProductCatalogue;
import senlenium_udemy.functional_tests.testComponents.BaseTest;
import senlenium_udemy.functional_tests.testComponents.Retry;

public class standAloneWithParameters extends BaseTest {

	@Test(dataProvider = "getDataWithMap", groups = { "parameterised" }, retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException {

		ProductCatalogue p = l.login(input.get("email"), input.get("pswd"));

		Payment pm = p.checkOut(input.get("productName"));
		String[] fields = { "CVV", "Name" };
		String[] values = { "133", "John" };
		for (int i = 0; i < fields.length; i++) {
			pm.EnterField(fields[i], values[i]);
		}

		Confirmation c = pm.enterPaymentDetails("ind");
		Assert.assertEquals("THANKYOU FOR THE ORDER.", c.confirmMsg());

	}

	// public Object[][] getData(){
	// return new Object[][]
	// {
	// {"testemailxyz@gmail.com", "Email@com1", "ADIDAS ORIGINAL"},
	// {"testemail001@gmail.com","Email@com2","ZARA COAT 3"}
	// };
	// }
	@DataProvider
	public Object[][] getDataWithMap() throws IOException {
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("email", "testemailxyz@gmail.com");
//			map.put("pswd",  "Email@com1");
//			map.put("productName", "ADIDAS ORIGINAL");
//			
//			HashMap<String, String> map1 = new HashMap<String, String>();
//			map1.put("email", "testemailxyz@gmail.com");
//			map1.put("pswd",  "Email@com1");
//			map1.put("productName", "ZARA COAT 3");
		List<HashMap<String, String>> data = getJsonToString(
				System.getProperty("user.dir") + "\\src\\test\\java\\selenium_udemy\\functional_tests\\data.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	
}
