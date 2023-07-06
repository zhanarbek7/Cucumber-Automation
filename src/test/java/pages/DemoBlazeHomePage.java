package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class DemoBlazeHomePage {

    public DemoBlazeHomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "signin2")
    public WebElement signUpBtn;

    @FindBy(id = "sign-username")
    public WebElement formUsernameInputBox;

    @FindBy(id = "sign-password")
    public WebElement formPasswordInputBox;

    @FindBy(xpath = "//button[.='Sign up']")
    public WebElement formSignUpBtn;

    @FindBy(id = "login2")
    public WebElement loginBtn;

    @FindBy(id = "loginusername")
    public WebElement loginUsernameInputBox;

    @FindBy(id = "loginpassword")
    public WebElement loginPasswordInputBox;

    @FindBy(xpath = "//button[.='Log in']")
    public WebElement formLoginBtn;

    @FindBy(xpath = "//div[@id='tbodyid']//h4/a")
    public List<WebElement> products;

    @FindBy(linkText = "Add to cart")
    public WebElement addToCartBtn;

    @FindBy(xpath = "//div[@id='tbodyid']/h2")
    public WebElement productTitle;

    @FindBy(xpath = "//div[@id='tbodyid']/h3")
    public WebElement productPrice;

    @FindBy(id = "cartur")
    public WebElement cartButton;

    @FindBy(xpath = "//tbody//td[2]")
    public WebElement firstProductTitle;

    @FindBy(xpath = "//tbody//td[3]")
    public WebElement getFirstProductPrice;

}
