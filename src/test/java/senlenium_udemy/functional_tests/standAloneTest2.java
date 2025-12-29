package senlenium_udemy.functional_tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import senlenium_udemy.functional_tests.pageObject.Confirmation;
import senlenium_udemy.functional_tests.pageObject.Payment;
import senlenium_udemy.functional_tests.pageObject.ProductCatalogue;
import senlenium_udemy.functional_tests.testComponents.BaseTest;
import senlenium_udemy.functional_tests.testComponents.Retry;

public class standAloneTest2 extends BaseTest {

	
	@Test(retryAnalyzer=Retry.class)
	public void submitOrder() throws IOException  {
		
		String email = "testemailxyz@gmail.com";
		String pswd =  "Email@com1";
		String productName = "ADIDAS ORIGINAL";
		
		ProductCatalogue p = l.login(email, pswd);

		Payment pm = p.checkOut(productName);
		String[] fields = {"CVV", "Name"};
		String[] values = {"133", "John"};
		for(int i=0; i<fields.length; i++) {
			pm.EnterField(fields[i], values[i]);
		}
		
		Confirmation c = pm.enterPaymentDetails("ind");
		Assert.assertEquals("THANKYOU FOR THE ORDER.", c.confirmMsg());

       
	}
	

}
