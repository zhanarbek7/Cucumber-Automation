package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class MVNRepositoryHomePage {

    public MVNRepositoryHomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "query")
    public WebElement searchInputBox;

    @FindBy(xpath = "//h2[@class='im-title']")
    public List<WebElement> searchResultTitles;

}
