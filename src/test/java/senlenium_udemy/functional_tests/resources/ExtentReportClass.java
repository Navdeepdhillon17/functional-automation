package senlenium_udemy.functional_tests.resources;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportClass {

    @Test
    public static ExtentReports getExtentObject() {
    	String path = System.getProperty("user.dir")+"//reports//index.html";
    	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
    	reporter.config().setDocumentTitle("extent report");
    	reporter.config().setReportName("new report");
    	
    	ExtentReports extent = new ExtentReports();
    	extent.attachReporter(reporter);
    	return extent;
    }
}
