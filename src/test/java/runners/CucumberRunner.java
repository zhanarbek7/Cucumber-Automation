package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {"pretty","html:target/cucumber-reports/report.html"},
        dryRun = false,
        tags = "@smoke"
)

public class CucumberRunner {
}
