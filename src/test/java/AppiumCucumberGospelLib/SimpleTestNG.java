package AppiumCucumberGospelLib;

import AppiumCucumberGospelLib.Screen.SimpleTestScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTestNG extends BaseDriver {
    @Test(groups = "simpleNG")
    public void simpleBookmark() throws Exception {
        SimpleTestScreen mySimpleTest = new SimpleTestScreen(driver);
        BasePage myBasePage = new BasePage(driver);

//        Thread.sleep(30000);
        //Wait for the Updating page to be gone
        myBasePage.waitUntilTextIsGone("Updating");

        //Skip the Whats New
        if (myBasePage.checkForElement(mySimpleTest.skipButton)) {
            mySimpleTest.skipButton.click();
            mySimpleTest.doneButton.click();
        }


        //Select Scriptures
        mySimpleTest.mainScriptures.click();

        //Select Book Of Mormon
        mySimpleTest.scripturesBookOfMormon.click();

        //Select Jacob
        mySimpleTest.bookOfMormonJacob.click();

        //Select Chapter 2
        mySimpleTest.chapter2.click();
        Thread.sleep(8000);

        //Menu > Select Bookmark
        mySimpleTest.menuBookmark.click();
        Thread.sleep(2000);
        Assert.assertTrue(myBasePage.checkForText("No Bookmarks"));

        //Select Add Bookmark Button ( + sign)
        mySimpleTest.bookmarkAdd.click();

        //Select the Add Bookmark button from the dialog
        mySimpleTest.addBookmarkAddButton.click();

        //Check the bookmark
        mySimpleTest.menuBookmark.click();
        Thread.sleep(2000);
        Assert.assertTrue(myBasePage.checkForText("Jacob 2"));
        Assert.assertFalse(myBasePage.checkForText("No Bookmarks"));

        //Delete the bookmark
        mySimpleTest.bookmarkOverflow.click();
        mySimpleTest.bookmarkDelete.click();

        mySimpleTest.bookmarkConfirmDelete.click();

        Assert.assertTrue(myBasePage.checkForText("No Bookmarks"));
        Assert.assertFalse(myBasePage.checkForText("Jacob 2"));


        //Select New Testament


        //Select Mark


        //Select Chapter 4


        //Add Bookmark
    }
}
