package steps.ui;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;
import utilities.MyLogger;

public class Hooks {

    @Before
    public void setup(Scenario scenario){
        MyLogger.info("Scenario: " + scenario.getName() + " is starting");
    }
    @After
    public void cleanUp(Scenario scenario){

        if (scenario.isFailed()){
            MyLogger.error("Scenario FAILED");
            byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        else{
            MyLogger.info("Scenario PASSED");
        }
        Driver.quitBrowser();
    }
}
