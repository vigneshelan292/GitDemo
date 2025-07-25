package cucumber.options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",            // Path to your .feature files
    glue = "stepdefinitions",                       // Package containing step definitions
    plugin = {
        "json:target/jsonReports/cucumber-report.json",  // For Maven Cucumber Reporting
        "html:target/htmlReports"                         // Optional: built-in HTML report
    },
    monochrome = true                                // Cleaner console output
)
public class TestRunner {
	
	
}