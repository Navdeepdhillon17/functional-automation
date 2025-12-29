package selenium_udemy.functional_step_definition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import senlenium_udemy.functional_tests.standAloneWithParameters;
import senlenium_udemy.functional_tests.pageObject.Confirmation;
import senlenium_udemy.functional_tests.pageObject.LandingPage;
import senlenium_udemy.functional_tests.pageObject.Payment;
import senlenium_udemy.functional_tests.pageObject.ProductCatalogue;
import senlenium_udemy.functional_tests.testComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
//	public LandingPage l;
	ProductCatalogue p;
	Payment pm;
	Confirmation c;

	@Given("I am on the ecommerce homepage")
	public void i_am_on_the_eccomerce_landing_page() throws IOException {
		launchApplicaiton();
	}

	@Given("^User is logged in with username (.+) and password (.+)$")
	public void user_is_logged_in_with_username_and_password(String username, String password) {
		p = l.login(username, password);
	}

	@When("^the product is (.+) and submit button is clicked$")
	public void the_product_is_added_to_the_cart(String prodName) {
		pm = p.checkOut(prodName);
		String[] fields = { "CVV", "Name" };
		String[] values = { "133", "John" };
		for (int i = 0; i < fields.length; i++) {
			pm.EnterField(fields[i], values[i]);
		}
	}

	@Then("Verify the confirmation message {string} is present on the page")
	public void verify_the_confirmation_message_is_present_on_the_page(String string) {
		c = pm.enterPaymentDetails("ind");                 
		Assert.assertEquals(string, c.confirmMsg()); 
	}

}
