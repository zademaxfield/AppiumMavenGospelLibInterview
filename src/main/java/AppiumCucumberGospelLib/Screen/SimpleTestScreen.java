package AppiumCucumberGospelLib.Screen;

import AppiumCucumberGospelLib.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SimpleTestScreen extends BasePage {

    public SimpleTestScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        Duration myDuration = Duration.ofSeconds(10);
        PageFactory.initElements(new AppiumFieldDecorator(driver, myDuration), this);
    }


    //******* Updating Gospel Library **********

    //Progress Bar
    @AndroidFindBy(id = "startupProgressBar")
    public MobileElement startupProgressBar;

    //Progress Message Text
    @AndroidFindBy(id = "messageTextView")
    public MobileElement startupMessageText;


    //******* Updating Gospel Library **********
    //Skip Button
    @AndroidFindBy(id= "skip")
    public MobileElement skipButton;

    //Done Button
    @AndroidFindBy(id= "done")
    public MobileElement doneButton;



    //******* Main Library **********

    //Scriptures
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Scriptures']")
    public MobileElement mainScriptures;


    //Book of Mormon
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Book of Mormon']")
    public MobileElement scripturesBookOfMormon;


    //Jacob
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Jacob']")
    public MobileElement bookOfMormonJacob;


    //Chapter 2
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='2']")
    public MobileElement chapter2;


    //******* Menu Bar **********

    //Search
    @AndroidFindBy(id = "menu_item_search")
    public MobileElement menuSearch;

    //Bookmark
    @AndroidFindBy(id = "menu_item_bookmark")
    public MobileElement menuBookmark;


    //******* Bookmark Page  **********

    //Bookmark Add
    @AndroidFindBy(id = "locationsFab")
    public MobileElement bookmarkAdd;

    //Bookmark overflow
    @AndroidFindBy(id = "menuView")
    public MobileElement bookmarkOverflow;

    //Bookmark Update
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Update']")
    public MobileElement bookmarkUpdate;

    //Bookmark Rename
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Rename']")
    public MobileElement bookmarkRename;

    //Bookmark Delete
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
    public MobileElement bookmarkDelete;

    //Bookmark Confirm Delete
    @AndroidFindBy(id = "md_button_positive")
    public MobileElement bookmarkConfirmDelete;



    //******* Add Bookmark Dialog  **********

    //Add Bookmark Title
    @AndroidFindBy(id = "md_text_title")
    public MobileElement addBookmarkTitle;

    //Add Bookmark Message
    @AndroidFindBy(id = "md_input_message")
    public MobileElement addBookmarkMessage;

    //Add Bookmark Default
    @AndroidFindBy(id = "md_button_neutral")
    public MobileElement addBookmarkDefaulButtont;

    //Add Bookmark Cancel
    @AndroidFindBy(id = "md_button_negative")
    public MobileElement addBookmarkCancelButton;

    //Add Bookmark Add
    @AndroidFindBy(id = "md_button_positive")
    public MobileElement addBookmarkAddButton;


}
