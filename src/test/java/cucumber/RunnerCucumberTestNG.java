package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber/Submit.feature", glue="selenium_udemy.functional_step_definition", 
monochrome=true, tags="Regression", plugin= {"html:target/cucumber.html"})

public class RunnerCucumberTestNG extends AbstractTestNGCucumberTests{

}
