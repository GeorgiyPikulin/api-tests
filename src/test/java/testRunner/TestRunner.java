package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = ".//src/test/features/",
        glue = "stepDefinitions",
        plugin = {"pretty", "html:target/reports/myreport.html", "json:target/reports/myreport.json"},
        dryRun = false
//        monochrome = true,
//        tags = "@DeletePlace"
)
public class TestRunner {
}
