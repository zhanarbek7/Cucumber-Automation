package steps.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.DemoBlazeHomePage;
import utilities.Driver;
import utilities.Flow;
import utilities.TempStorage;

public class DemoBlazeLogin_steps {
    DemoBlazeHomePage demoBlazeHomePage = new DemoBlazeHomePage();
    @And("user clicks on log in button for demoblaze")
    public void userClicksOnLoginButtonForDemoBlaze() {
        demoBlazeHomePage.loginBtn.click();
        Flow.wait(500);
    }

    @When("user enters credentials {string} and {string} and clicks login")
    public void userEntersCredentialsAndAndClicksLogin(String username, String password) {
        TempStorage.addData("username", username);
        demoBlazeHomePage.loginUsernameInputBox.sendKeys(username);
        demoBlazeHomePage.loginPasswordInputBox.sendKeys(password);
        demoBlazeHomePage.formLoginBtn.click();
        Flow.wait(1000);
    }

    @Then("user should successfully login to application")
    public void userShouldSuccessfullyLoginToApplication() {
        String username = TempStorage.getData("username");
        String linkText = "Welcome " + username;
        WebElement welcomeUser = Driver.getDriver().findElement(By.linkText(linkText));
        Assert.assertTrue(welcomeUser.isDisplayed());
    }
}
