package steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.MVNRepositoryHomePage;
import utilities.Driver;

public class MvnSearch_steps {

    @Given("user is on mvnrepository application")
    public void user_is_on_mvnrepository_application() {
        Driver.getDriver().get("https://mvnrepository.com");
    }

    @Given("user searches for {string} on mvn application")
    public void user_searches_for_on_mvn_application(String word) {
        MVNRepositoryHomePage mvnRepositoryHomePage = new MVNRepositoryHomePage();
        mvnRepositoryHomePage.searchInputBox.sendKeys(word + Keys.ENTER);
    }

    @Then("user should see only {string} related results")
    public void user_should_see_only_related_results(String word) {
        MVNRepositoryHomePage mvnRepositoryHomePage = new MVNRepositoryHomePage();
        for (WebElement title: mvnRepositoryHomePage.searchResultTitles){
            String result = title.getText().toLowerCase();
            Assert.assertTrue(result.contains(word));
        }
    }

}
