package stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import endPoints.ExternalEndpoints;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.assertj.core.api.StrictAssertions;
import org.junit.jupiter.api.AfterEach;
import pageObjects.HomePage;
import pageObjects.LoginPage;
//import pageObjects.HomePage;
//import pageObjects.ProductsInvestingPage;
//import pageObjects.ProductsRewardsPage;

import org.junit.runner.RunWith;

//import static base.Base.getDriver;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import base.APIResources;
import base.Base;
import pageObjects.LoginPage;
import sun.jvmstat.monitor.event.HostEvent;
//import base.TestDataBuilder;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(Cucumber.class)
public class StepDefinitions extends Base {

    LoginPage loginPage;
    ExternalEndpoints externalEndpoints;
    HomePage homePage;
    Long projectId;
    List<String> taskId;

    @Given("^I have created the new project \"([^\"]*)\" via \"([^\"]*)\"$")
    public void i_have_created_the_new_project(String projectName, String endpoint) throws Throwable {
        driver = getAndroidDriver();
        externalEndpoints = new ExternalEndpoints(driver);

        projectId = externalEndpoints.apiCreateProject(projectName,endpoint);
    }

    @Given("^I login to the mobile application")
    public void i_login_to_mobile_application222() throws MalformedURLException {
        driver = getAndroidDriver();
        loginPage = new LoginPage(driver);
        loginPage.performLogin();
    }

    @When("^I create the task \"([^\"]*)\" via mobile application for project \"([^\"]*)\"$")
    public void i_create_the_task_via_mobile_app(String taskName, String projectName) throws MalformedURLException {
        driver = getAndroidDriver();
        homePage = new HomePage(driver);

        homePage.createTaskInProject(taskName,projectName);
    }

    @When("^I get my taskId via \"([^\"]*)\" to complete the task \"([^\"]*)\"$")
    public void i_complete_the_task(String endPoint, String taskName) throws MalformedURLException {
        driver = getAndroidDriver();

        externalEndpoints = new ExternalEndpoints(driver);
        taskId = externalEndpoints.apiGetTask(projectId,taskName,endPoint);

        homePage = new HomePage(driver);
        homePage.completeTask(taskName);

    }

    @And("^I reopen the completed task via \"([^\"]*)\"$")
    public void i_reopen_the_task(String endpoint) throws MalformedURLException {
        driver = getAndroidDriver();
        externalEndpoints = new ExternalEndpoints(driver);

       externalEndpoints.apiReopenTask(taskId,endpoint);
    }

    @Then("^I am able to see the project \"([^\"]*)\" is listed in the project list$")
    public void i_am_able_to_see_project_in_project_list(String projectName) throws MalformedURLException {

        driver = getAndroidDriver();
        homePage = new HomePage(driver);
        homePage.selectProjectNameFromList(projectName);
        /*
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        externalEndpoints = new ExternalEndpoints(driver);
        externalEndpoints.apiDeleteProject(projectId);
    }

    @And("^I logout the mobile application successfully$")
    public void i_logout_the_mobile_app() throws MalformedURLException, InterruptedException {
        driver = getAndroidDriver();
        homePage = new HomePage(driver);
        homePage.logoutFromMobile();

        externalEndpoints = new ExternalEndpoints(driver);
        externalEndpoints.apiDeleteProject(projectId);
        /*
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    @Then("^I am able to verify my task \"([^\"]*)\" via \"([^\"]*)\"$")
        public void i_am_able_verify_my_task_via_api(String taskName, String endpoint) throws MalformedURLException {
        driver = getAndroidDriver();
        externalEndpoints = new ExternalEndpoints(driver);

        taskId = externalEndpoints.apiGetTask(projectId,taskName,endpoint);
    }

    @Then("I am able to see the task \"([^\"]*)\" is listed in the project \"([^\"]*)\"$")
        public void i_am_able_to_see_the_task(String taskName, String projectName) throws MalformedURLException {

        driver = getAndroidDriver();
        homePage = new HomePage(driver);
        homePage.selectTaskNameFromList(taskName,projectName);
    }


//
//    @AfterEach
//    public void tearDown() {
//        driver.close();
//    }
}