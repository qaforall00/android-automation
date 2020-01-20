package pageObjects;

import base.Base;
import base.NativeActions.NativeActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends Base {

    WebDriverWait wait;
    NativeActions ntvActions;
    AndroidDriver localDriver;

    By btnContinueEmail = By.xpath("//*[@text='Continue with email']");
    By fieldEmail = By.id("com.todoist:id/email_exists_input");
    By fieldPassword = By.id("com.todoist:id/log_in_password");
    By btnLogin = By.id("com.todoist:id/btn_log_in");


    public LoginPage(AndroidDriver driver){
        localDriver = driver;
        wait = new WebDriverWait(localDriver, 30);
        ntvActions = new NativeActions(localDriver);
    }

    public void performLogin(){
        ntvActions.performClickOnWebElement(localDriver,btnContinueEmail);
        ntvActions.performTypeOnWebElement(localDriver,fieldEmail,prop.getProperty("user_name"));
        ntvActions.performClickOnWebElement(localDriver,btnContinueEmail);
        ntvActions.performTypeOnWebElement(localDriver,fieldPassword,prop.getProperty("user_password"));
        ntvActions.performClickOnWebElement(localDriver,btnLogin);
    }
}
