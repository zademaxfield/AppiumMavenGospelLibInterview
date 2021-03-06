package AppiumCucumberGospelLib;


import io.appium.java_client.*;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public AppiumDriver<MobileElement> driver;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        Duration myDuration = Duration.ofSeconds(10);
        PageFactory.initElements(new AppiumFieldDecorator(driver, myDuration), this);

    }

    public void waitUnitlElementIsGone(MobileElement myElement) {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.invisibilityOf(myElement));
    }

    public void waitUntilTextIsGone(String myText) {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text, '" + myText + "')]")));

    }


    public boolean checkForText(String myText) {
        String myPageSource;
        myPageSource = driver.getPageSource();
        if (myPageSource.contains(myText)) {
            return true;
        }
        return false;
    }




    //TODO: Need a faster way to do this.
    public boolean checkForElement(MobileElement myElement ) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(myElement));
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public String getOS() {
        String osName = "test";
        //System.out.println("Context: " + driver.getContext());
        //System.out.println("OS: " + osName);
        osName = driver.getCapabilities().getCapability("platformName").toString();
        osName = osName.toLowerCase();
        //System.out.println("OS: " + osName);
        return osName;
    }

    public void waitForText(String myText) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text, '" + myText + "')]")));
    }



}
