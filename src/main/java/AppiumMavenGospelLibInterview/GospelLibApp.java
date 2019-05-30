package AppiumMavenGospelLibInterview;

import AppiumMavenGospelLibInterview.Screen.SimpleTestScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class GospelLibApp {
    private final AppiumDriver<MobileElement> driver;

    public GospelLibApp(AppiumDriver<MobileElement> driver) {this.driver = driver;}

    //Simple Test
    public SimpleTestScreen simpleTest() {return new SimpleTestScreen(driver); }

}
