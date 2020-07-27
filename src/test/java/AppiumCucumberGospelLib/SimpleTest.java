package AppiumCucumberGospelLib;

import AppiumCucumberGospelLib.Screen.SimpleTestScreen;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class SimpleTest extends BaseDriver {
    SimpleTestScreen mySimpleTest = new SimpleTestScreen(driver);
    BasePage myBasePage = new BasePage(driver);

    @Given("^I am on Jacob chapter 2")
    public void goToJacobChapter2() {
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
    }

    @When("^I click the \"(.*)\" button")
    public void clickButton(String arg1) throws Exception {
        switch(arg1) {
            case "bookmark":
                mySimpleTest.menuBookmark.click();
                break;
            case "plus": // This is the + sign
                mySimpleTest.bookmarkAdd.click();
                break;
            case "add":
                mySimpleTest.addBookmarkAddButton.click();
                break;
            case "bookmark overflow":
                mySimpleTest.bookmarkOverflow.click();
                break;
            case "delete":
                mySimpleTest.bookmarkDelete.click();
                break;
            case "delete confirm":
                mySimpleTest.bookmarkConfirmDelete.click();
                break;
            default:
                System.out.println("Button Not Defined!");
        }
    }

    @Then("^I should see \"(.*)\"")
    public void checkTextFound(String arg1) {
        myBasePage.waitForText(arg1);
        Assert.assertTrue(myBasePage.checkForText(arg1));
    }

    @Then("^I should not see \"(.*)\"")
    public void checkTextNotFound(String arg1) {
        Assert.assertFalse(myBasePage.checkForText(arg1));
    }


}
