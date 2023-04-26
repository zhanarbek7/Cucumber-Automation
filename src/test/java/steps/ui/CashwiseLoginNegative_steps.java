package steps.ui;

import pages.CashwiseLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.Driver;
import utilities.MyLogger;

public class CashwiseLoginNegative_steps {
    CashwiseLoginPage cashwiseLoginPage = new CashwiseLoginPage();
    @Given("user clicks on log in button")
    public void user_clicks_on_log_in_button() {
        cashwiseLoginPage.signInLink.click();
    }
    @When("user logs in {string} and {string}")
    public void user_logs_in_and(String email, String password) {
        MyLogger.warn("Logging into software");
        cashwiseLoginPage.emailInputBox.sendKeys(email);
        cashwiseLoginPage.passwordInputBox.sendKeys(password);
        cashwiseLoginPage.signInButton.submit();
    }
    @Then("user should land on {string} page")
    public void user_should_land_on_page(String url) {
        MyLogger.warn("Verifying landing on homepage page");
        String actualURL = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(url, actualURL);
    }


    @Then("user sees error messages {string} and {string}")
    public void userSeesErrorMessagesAnd(String emailError, String passwordError) {
        MyLogger.warn("Verifying error messages");
        String actualEmailError = cashwiseLoginPage.emailErrorMessage.getText().trim();
        String actualPasswordError = cashwiseLoginPage.passwordErrorMessage.getText().trim();
        System.out.println(actualEmailError + " " + actualPasswordError);
        System.out.println(emailError + " " + passwordError);
        Assert.assertEquals(emailError, actualEmailError);
        Assert.assertEquals(passwordError, actualPasswordError);
    }
}
