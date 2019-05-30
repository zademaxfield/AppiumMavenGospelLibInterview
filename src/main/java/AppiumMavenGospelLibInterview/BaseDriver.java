package AppiumMavenGospelLibInterview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.logging.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.*;

public class BaseDriver {
    public AppiumDriver<MobileElement> driver;
    public String testOS = "";
    public AppiumService myAppiumService = new AppiumService();

    protected GospelLibApp app;

    public String androidAppPackage = "org.lds.ldssa";



    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "fileName", "testDevice", "startSleepTime"})
    public void setUp(String os, String fileName, String testDevice, int startSleepTime) throws Exception {

        int myPort;
        testOS = os;

        //Sleep so when multiple tests start they don't break
        System.out.println("Sleep Time: " + startSleepTime);
        Thread.sleep(startSleepTime);

        //Get Random Port Number
        myPort = getRandomPort();

        AppiumService.startAppiumService(os, myPort);


        driver = appiumCapabilities(os, fileName, testDevice, myPort);

        app = new GospelLibApp(driver);

        
    }



    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) throws Exception {
        BasePage myBasePage = new BasePage(driver);
        String testName;

        System.out.println("Start teardown");
        driver.resetApp();
        System.out.println("End teardown");
    }




    @AfterSuite(alwaysRun = true)
    public void afterAllTests() throws Exception {
        myAppiumService.stopAppiumService();

        Thread.sleep(1000);
        System.out.println("Killing the Appium Service");
        killProcess("main.js");

    }

    private AppiumDriver<MobileElement> appiumCapabilities(String os, String fileName, String testDevice, int myPort) throws Exception {
        String myAppPackage;


        // set up Appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../../../Selenium");
        File app = new File(appDir, fileName);

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "org.lds.ldssa");
        myAppPackage = "org.lds.ldssa";
        androidAppPackage = myAppPackage;

        capabilities.setCapability("deviceName", testDevice);
        capabilities.setCapability("udid", testDevice);
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("automationName","uiautomator2");
        capabilities.setCapability("appPackage", myAppPackage);
        capabilities.setCapability("newCommandTimeout", 2000);


        capabilities.setCapability("unicodeKeyboard", "true");
        capabilities.setCapability("resetKeyboard", "true");
        capabilities.setCapability("deviceReadyTimeout", 60);
        capabilities.setCapability("noSign", true);

        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("dontStopAppOnReset", true);
        //capabilities.setCapability("maxTypingFrequency", "8");

        capabilities.setCapability("normalizeTagNames", true);

        capabilities.setCapability("clearDeviceLogsOnStart", true);

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:" + myPort + "/wd/hub"), capabilities);

        Thread.sleep(2000);

        return driver;
    }

    private int getRandomPort() throws Exception {

        Random randomPort = new Random();
        int myPort;
        int lowPort = 4500;
        int highPort = 4999;

        Boolean portOpen;

        //Check to see if the random port is open
        //If the port is in use try a different port
        do {
            myPort = randomPort.nextInt(highPort - lowPort) + lowPort;
            portOpen = portCheck(myPort);
        } while (portOpen.equals(false));

        return myPort;

    }



    private boolean portCheck(int port) throws IOException {
        // Assume no connection is possible.
        boolean result = false;

        try {
            Socket s = new Socket("localhost", port);
            s.close();
            System.out.println("Port " + port + " is in use.");
            result = false;
        }
        catch(SocketException e) {
            // Could not connect.
            //System.out.println("Port " + port + " is open.");
            result = true;
        }

        return result;
    }


    public void killProcess(String processToKill) throws Exception {
        String line = "";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(new String[] {"/usr/bin/pkill", "-9", "-f", "-l", processToKill});
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        while ((line=buf.readLine())!=null) {
            System.out.println(line);
        }
    }


    public String getRunningOS() {
        String myOs = "";

        if (testOS.equalsIgnoreCase("android")) {
            myOs = "android";
        }

        if (testOS.equalsIgnoreCase("ios")) {
            myOs = "ios";
        }

        myOs = myOs.toLowerCase();
        //System.out.println("MY OS: "  + myOs);
        return myOs;
    }

    private void adbCommand(String myCommand) throws Exception {
        String androidHome = System.getenv("ANDROID_HOME");
        String pathToADB = androidHome + "/platform-tools/adb";



        if (myCommand.equals("stopApp")) {
            //String cmd = "adb shell am force-stop org.lds.ldstools.alpha";
            Runtime run = Runtime.getRuntime();
//            Process pr = run.exec(new String[] {pathToADB, "shell", "am", "force-stop", "org.lds.ldstools.alpha"});
            Process pr = run.exec(new String[] {pathToADB, "shell", "am", "force-stop", androidAppPackage});
            //Process pr = run.exec(cmd);
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            while ((line=buf.readLine())!=null) {
                System.out.println(line);
            }
        }

        if (myCommand.equals("clearApp")) {
            //String cmd = "adb shell am force-stop org.lds.ldstools.alpha";
            Runtime run = Runtime.getRuntime();
            Process pr = run.exec(new String[] { pathToADB, "shell", "pm", "clear", androidAppPackage});
            //Process pr = run.exec(cmd);
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            while ((line=buf.readLine())!=null) {
                System.out.println(line);
            }
        }
    }

    public void takeScreenShot() {
        String imagesLocation = "screenshot/";
        new File(imagesLocation).mkdirs(); // Insure directory is there

        try {
            File srcFile=driver.getScreenshotAs(OutputType.FILE);
            String filename= UUID.randomUUID().toString();
            System.out.println("Screenshot File: " + filename);
            File targetFile=new File(imagesLocation + filename +".png");
            FileUtils.copyFile(srcFile,targetFile);
        }
        catch(Exception e){
            System.out.println("Warning: Some Other exception");
        }

    }

    private void takeScreenShotDirectory(String filename, String imagesLocation) {
        try {
            File srcFile=driver.getScreenshotAs(OutputType.FILE);
            System.out.println("Screenshot File: " + filename);
            File targetFile=new File(imagesLocation + filename +".png");
            FileUtils.copyFile(srcFile,targetFile);
        }
        catch(Exception e){
            System.out.println("Warning: Some Other exception");
        }

    }


    private void screenshotAndLogs(String testName) throws Exception {
        LogEntries logEntries;
        List<String> myLogData = new ArrayList<String>();
        List<String> logTypes = new ArrayList<String>();
        //Get Random UUID
        String filename= UUID.randomUUID().toString();
        //Make DIR for random UUID
        String imagesLocation = "screenshot/" + filename +"/";
        String logFile = imagesLocation + testName + ".txt";
        new File(imagesLocation).mkdirs(); // Insure directory is there


        //Take Screen shot
        takeScreenShotDirectory(filename, imagesLocation);

        myLogData.add(testName);
        myLogData.add("******************* LOGS *********************");

        if (getRunningOS().contains("ios")) {
            //logTypes.add("syslog");
            logTypes.add("crashlog");
            logTypes.add("server");
        } else {
            logTypes.add("logcat");
//            logTypes.add("bugreport");
            logTypes.add("server");
        }

        //Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
        for(String myLog : logTypes) {
            //System.out.println(myLog);
            myLogData.add(" ******************* " + myLog +  " ******************* " );
            logEntries = driver.manage().logs().get(myLog);
            for (LogEntry entry : logEntries) {
                myLogData.add(entry.getMessage());
                //System.out.println(entry.getMessage());
            }

        }

        myLogData.add("******************* END LOGS *********************");

        PrintWriter pw = new PrintWriter(new FileOutputStream(logFile));
        for (String logItem : myLogData) {
            pw.println(logItem);
        }
        pw.close();

    }





}
