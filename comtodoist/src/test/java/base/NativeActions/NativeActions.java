package base.NativeActions;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NativeActions {

    WebDriverWait wait;

    public NativeActions(AndroidDriver driver) {
        wait = new WebDriverWait(driver, 10);
    }

    public void performClickUsingJavascript(AndroidDriver driver, By findBy) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()", driver.findElement(findBy));
    }

    public void performScrollIntoViewUsingJavascript(WebDriver driver, By findBy) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(findBy));
    }

    public void performClickOnWebElement(AndroidDriver driver, By findBy) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement((By) findBy)));
        driver.findElement(findBy).click();
    }

    public void performTypeOnWebElement(AndroidDriver driver, By findBy, String input) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement((By) findBy)));
        driver.findElement(findBy).sendKeys(input);
    }

    public void waitForVisibilityOfWebElement(WebDriver driver, By findBy) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(findBy)));
    }

    public void waitForVisibilityOfWebElement(WebDriver driver, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getTextOfWebElement(WebDriver driver, By findBy) {
        return driver.findElement(findBy).getText();
    }

    public WebElement findWebElement(WebDriver driver, By findBy) {
        return driver.findElement(findBy);
    }

    public List<WebElement> findWebElements(WebDriver driver, By findBy) {
        return driver.findElements(findBy);
    }

    public String getAttributeValueOfWebElement(WebDriver driver, By findBy, String attribute) {
        return driver.findElement(findBy).getAttribute(attribute);
    }
}
