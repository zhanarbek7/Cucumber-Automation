package steps.ui;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.CashwiseLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;

import java.sql.DriverManager;
import java.util.List;

public class CashwiseBenefits_steps {

    @After(order = 1)
    public void takeScraenshotOnFailure(Scenario scenario) {

        if (scenario.isFailed()) {

            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();

            byte[] src = ts.getScreenshotAs(OutputType.BYTES.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }

    }

    @Given("user is on cashwise application")
    public void userIsOnCashwiseApplication() {
        Driver.getDriver().get(Config.getValue("cashwiseURL"));
        Flow.wait(5000);
    }

    @When("user scrolls down to four offer benefits on homepage")
    public void user_scrolls_down_to_four_offer_benefits_on_homepage() {
        // Write code here that turns the phrase above into concrete actions
        Flow.scrollDown(800);
        Flow.wait(3000);
    }

    @Then("user should see all four options of the offer benefits")
    public void user_should_see_all_four_options_of_the_offer_benefits(List<String> benefits) {
        CashwiseLoginPage cashwiseLoginPage = new CashwiseLoginPage();
        for (WebElement benefit : cashwiseLoginPage.benefitList) {
            String benefitString = benefit.getText().trim();
            System.out.println(benefitString);
            Assert.assertTrue(benefits.contains(benefitString));
        }
    }
}
