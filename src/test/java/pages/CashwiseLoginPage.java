package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class CashwiseLoginPage {

    public CashwiseLoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//button)[1]")
    public WebElement signInLink;

    @FindBy(xpath = "(//input[@name='email'])[2]")
    public WebElement emailInputBox;

    @FindBy(xpath = "(//input[@name='password'])")
    public WebElement passwordInputBox;

    @FindBy(xpath = "(//button[.='Sign in'])[2]")
    public WebElement signInButton;

    @FindBy(xpath = "((//form)[2]//p)[1]")
    public WebElement emailErrorMessage;

    @FindBy(xpath = "((//form)[2]//p)[2]")
    public WebElement passwordErrorMessage;

    @FindBy(xpath = "//h2[.='We offer']/..//h3")
    public List<WebElement> benefitList;



}
