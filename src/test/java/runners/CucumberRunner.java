package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {"pretty","html:target/cucumber-reports/report.html"},
        monochrome = true,
        publish = true,
        tags = "@SignUp or @google"
)

//json:target/cucumber-reports/report2.json
//junit:target/cucumber-reports/report3.xml
public class CucumberRunner {
}
