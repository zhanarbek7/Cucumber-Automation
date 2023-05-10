package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;
import utilities.MyLogger;

public class CommonHooks {



    @After
    public void cleanUp(Scenario scenario){
        if(scenario.isFailed()){
            MyLogger.error("Scenario FAILED");
            byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }else {
            MyLogger.info("Scenario Passed");
        }
        Driver.quitBrowser();
    }


}
