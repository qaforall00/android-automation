package pageObjects;

import base.Base;
import base.NativeActions.NativeActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class HomePage extends Base {

    WebDriverWait wait;
    AndroidDriver localDriver;
    NativeActions ntvActions;

    By btnChangeCurrentView = By.xpath("//android.widget.ImageButton[@content-desc='Change the current view']");
    By textProject =  By.xpath("//android.widget.TextView[@text='Projects']");
    By textTask = By.id("com.todoist:id/text");
    By textSettings =  By.xpath("//android.widget.TextView[@text='Settings']");
    By btnTask = By.id("com.todoist:id/fab");
    By fieldTaskName = By.id("android:id/message");
    By btnAddTask = By.id("android:id/button1");
    By toolbarContainer = By.id("com.todoist:id/content_toolbar_container");
    By emptyContent = By.id("com.todoist:id/empty_content");
    By btnYes = By.id("android:id/button1");
    By checkMarkComplete = By.id("com.todoist:id/checkmark");


    public HomePage (AndroidDriver driver) {
        localDriver = driver;
        wait = new WebDriverWait(localDriver, 30);
        ntvActions = new NativeActions(localDriver);
    }

    public void selectProjectNameFromList(String projectName) {

        By textProjectName = By.xpath("//android.widget.TextView[@text='" + projectName + "']");

        ntvActions.waitForVisibilityOfWebElement(localDriver, btnChangeCurrentView);
        ntvActions.performClickOnWebElement(localDriver, btnChangeCurrentView);
        ntvActions.performClickOnWebElement(localDriver, textProject);
        ntvActions.performClickOnWebElement(localDriver, textProjectName);
        assertEquals(projectName, ntvActions.getTextOfWebElement(localDriver,textProjectName));
    }

    public void logoutFromMobile() throws InterruptedException {
        ntvActions.performClickOnWebElement(localDriver,emptyContent);
        ntvActions.waitForVisibilityOfWebElement(localDriver, btnChangeCurrentView);
        ntvActions.performClickOnWebElement(localDriver, btnChangeCurrentView);
        ntvActions.performClickOnWebElement(localDriver, textSettings);

        WebElement elementToClick = (WebElement) driver
                .findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                        + ".resourceId(\"android:id/list\")).scrollIntoView("
                        + "new UiSelector().text(\"Log out\"));");
        elementToClick.click();

        ntvActions.performClickOnWebElement(localDriver,btnYes);
    }

    public void createTaskInProject(String taskName, String projectName){
        selectProjectNameFromList(projectName);
        ntvActions.performClickOnWebElement(localDriver,btnTask);
        ntvActions.performTypeOnWebElement(localDriver,fieldTaskName,taskName);
        ntvActions.performClickOnWebElement(localDriver,btnAddTask);
        /*
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    public void completeTask(String taskName) {
        ntvActions.performClickOnWebElement(localDriver, toolbarContainer);
        ntvActions.performClickOnWebElement(localDriver,checkMarkComplete);
    }

    public void selectTaskNameFromList(String taskName, String projectName) {
        ntvActions.performClickOnWebElement(localDriver, textProject);
       selectProjectNameFromList(projectName);
       assertEquals(taskName,ntvActions.getTextOfWebElement(localDriver,textTask));
    }
}