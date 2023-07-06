package steps.ui;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.DemoBlazeHomePage;
import utilities.Driver;
import utilities.Flow;
import utilities.MyLogger;

import java.util.Random;

public class DemoBlazeSignUp_steps {
    DemoBlazeHomePage blazeHomePage = new DemoBlazeHomePage();

    @And("user clicks on sign up button")
    public void userClicksOnSignUpButton() {
        MyLogger.warn("Clicking the sign up link");
        blazeHomePage.signUpBtn.click();
        Flow.wait(500);
    }

    @When("user enters credentials {string} and {string} and clicks sign up")
    public void userEntersCredentialsAndAndClicksSignUp(String username, String password) {

        if (username.equals("placeholder")){
            username = username + new Random().nextInt(10000);
        }
        MyLogger.warn("Entering Credentials: username:" + username + " pass: " + password);

        blazeHomePage.formUsernameInputBox.sendKeys(username);
        Flow.wait(2000);
        blazeHomePage.formPasswordInputBox.sendKeys(password);
        blazeHomePage.formSignUpBtn.click();
        Flow.wait(1000);
    }

    @Then("user should see alert message {string}")
    public void userShouldSeeAlertMessage(String message) {
        MyLogger.info("Verifying the alert messages");
        Alert signUpAlert = Driver.getDriver().switchTo().alert();
        String actualMessage = signUpAlert.getText();
        System.out.println(actualMessage);
        System.out.println(message);
        Assert.assertEquals(message, actualMessage);
    }
}
