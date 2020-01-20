package base;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import base.NativeActions.NativeActions;
import io.appium.java_client.android.AndroidDriver;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

    public static Properties prop;
    public static RequestSpecification req;
    public static String method;
    public static AndroidDriver driver;

    public Base() {
        prop = new Properties();
        FileInputStream fis;

        try {
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AndroidDriver getAndroidDriver() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName", "Pixel XL API 29 ");
        dc.setCapability("APPLICATION_NAME", "com.todoist");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.setLogLevel(Level.INFO);
        return driver;
    }

    public static AndroidDriver getAndroidDriverReference() {
        return driver;
    }

    public static String getProjectURL() {
        String returnURL = "https://api.todoist.com/rest/v1";
        if(prop!=null)
            returnURL = prop.getProperty("projects_url");
        return returnURL;
    }

    public static String getTaskURL() {
        String returnURL = "https://api.todoist.com/rest/v1/tasks";
        if(prop!=null)
            returnURL = prop.getProperty("tasks_url");
        return returnURL;
    }

    public RequestSpecification requestSpecsification() throws IOException
    {
        if(req==null)
        {
            PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
            req=new RequestSpecBuilder().setBaseUri(prop.getProperty("projects_url"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public String getJsonPath(Response response,String key)
    {
        String resp=response.asString();
        JsonPath   js = new JsonPath(resp);
        return js.get(key).toString();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        Base.method = method;
    }

}
