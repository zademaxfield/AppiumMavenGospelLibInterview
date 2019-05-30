package AppiumMavenGospelLibInterview;

import AppiumMavenGospelLibInterview.BaseDriver;
import AppiumMavenGospelLibInterview.BasePage;
import AppiumMavenGospelLibInterview.Screen.SimpleTestScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest extends BaseDriver {

    @Test
    public void simpleBookmark() throws Exception {
        SimpleTestScreen mySimpleTest = new SimpleTestScreen(driver);
        BasePage myBasePage = new BasePage(driver);

        //Wait for the Updating page to be gone
        myBasePage.waitUnitlTextIsGone("Updating");

        //Select Scriptures
        mySimpleTest.mainScriptures.click();

        //Select Book Of Mormon
        mySimpleTest.scripturesBookOfMormon.click();

        //Select Jacob
        mySimpleTest.bookOfMormonJacob.click();

        //Select Chapter 2
        mySimpleTest.chapter2.click();

        //Menu > Select Bookmark
        mySimpleTest.menuBookmark.click();
        Assert.assertTrue(myBasePage.checkForText("No Bookmarks"));

        //Select Add Bookmark Button ( + sign)
        mySimpleTest.bookmarkAdd.click();

        //Select the Add Bookmark button from the dialog
        mySimpleTest.addBookmarkAddButton.click();

        //Check the bookmark
        mySimpleTest.menuBookmark.click();
        Assert.assertTrue(myBasePage.checkForText("Jacob 2"));
        Assert.assertFalse(myBasePage.checkForText("No Bookmarks"));

        //Delete the bookmark
        mySimpleTest.bookmarkOverflow.click();
        mySimpleTest.bookmarkDelete.click();

        mySimpleTest.bookmarkConfirmDelete.click();

        Assert.assertTrue(myBasePage.checkForText("No Bookmarks"));
        Assert.assertFalse(myBasePage.checkForText("Jacob 2"));

        //This is just for testing
        Thread.sleep(5000);



    }

}
