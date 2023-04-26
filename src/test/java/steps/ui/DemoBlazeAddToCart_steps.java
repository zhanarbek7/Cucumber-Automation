package steps.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.DemoBlazeHomePage;
import utilities.*;

public class DemoBlazeAddToCart_steps {
    DemoBlazeHomePage demoBlazeHomePage = new DemoBlazeHomePage();
    @Given("user is on demo blaze application")
    public void userIsOnDemoBlazeApplication() {
        MyLogger.info("User is on demo blaze website");
        Driver.getDriver().get(Config.getValue("demoblazeURL"));
    }

    @And("user clicks on the product {string}")
    public void userClicksOnTheProduct(String productTitle) {
        MyLogger.info(("Clicking on product: "+ productTitle));
        boolean found = false;
        for (WebElement product : demoBlazeHomePage.products) {
            System.out.println(product.getText());
            if(product.getText().equalsIgnoreCase(productTitle)){
                found = true;
                product.click();
                break;
            }
        }
        if(!found) {
            Assert.fail("Product: "+ productTitle+" is not found!");
        }
        Flow.wait(5000);
    }

    @When("user clicks add to cart button")
    public void userClicksAddToCartButton() {
        MyLogger.info("Adding the product to cart");
        String title = demoBlazeHomePage.productTitle.getText();
        String price = demoBlazeHomePage.productPrice.getText();
        TempStorage.addData("title", title);
        TempStorage.addData("price", price);
        demoBlazeHomePage.addToCartBtn.click();
        Flow.wait(2000);
    }

    @Then("user sees {string} message on alert and clicks ok")
    public void userSeesMessageOnAlertAndClicksOk(String actualAlertMessage) {
        MyLogger.info("Verifying alert message");
        String alertMessage = Driver.getDriver().switchTo().alert().getText();
        Assert.assertEquals(actualAlertMessage, alertMessage);
        Driver.getDriver().switchTo().alert().accept();
    }

    @And("user clicks on Cart button")
    public void userClicksOnCartButton() {
        MyLogger.info("Going to cart page");
        demoBlazeHomePage.cartButton.click();
    }

    @Then("user verifies the details of the added product")
    public void userVerifiesTheDetailsOfTheAddedProduct() {
        MyLogger.warn("Verification of the details of the product");
        Assert.assertEquals(TempStorage.getData("title"), demoBlazeHomePage.firstProductTitle.getText());
        Assert.assertTrue(TempStorage.getData("price").contains(demoBlazeHomePage.getFirstProductPrice.getText()));
    }
}
