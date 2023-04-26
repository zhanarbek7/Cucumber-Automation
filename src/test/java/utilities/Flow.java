package utilities;

import org.openqa.selenium.interactions.Actions;

public class Flow {

    public static void wait(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch(InterruptedException e){
            System.out.println("Interruption occurred!");
        }
    }

    public static void scrollDown(int deltaY){
        Actions actions = new Actions(Driver.getDriver());
        actions.scrollByAmount(0,deltaY).perform();
    }

    public static void scrollUp(int deltaY){
        Actions actions = new Actions(Driver.getDriver());
        actions.scrollByAmount(0,-deltaY).perform();
    }

}
