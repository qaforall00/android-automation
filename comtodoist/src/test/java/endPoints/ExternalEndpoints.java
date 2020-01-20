package endPoints;

import base.Base;
import base.NativeActions.NativeActions;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExternalEndpoints extends Base {

    AndroidDriver localDriver;
    WebDriverWait wait;

    public ExternalEndpoints(AndroidDriver driver){
        localDriver = driver;
        wait = new WebDriverWait(localDriver, 30);
    }

    public Long apiCreateProject(String projectName, String endPoint){
        RestAssured.baseURI = "https://api.todoist.com/rest/v1";
        RestAssured.basePath = endPoint;

        Response response = given()
                .header("Authorization","Bearer "+ prop.getProperty("bearer_token"))
                .header("Content-Type","application/json")
                .queryParam("name",projectName)
                .when()
                .post()
                .then().statusCode(200).body("id",notNullValue()).body("name", equalTo(projectName))
                .extract().response();

        Long projectId = response.path("id");

        return projectId;
    }

    public void apiDeleteProject(Long projectId){
        RestAssured.baseURI = "https://api.todoist.com/rest/v1";
        RestAssured.basePath = "projects/"+projectId;

        given()
                .header("Authorization","Bearer "+ prop.getProperty("bearer_token"))
                .delete()
                .then().statusCode(204);
    }

    public List<String> apiGetTask(Long projectId, String taskName, String endpoint){
        RestAssured.baseURI = "https://api.todoist.com/rest/v1";
        RestAssured.basePath = endpoint;

        List<String> tl =  new ArrayList<String>();
        tl = Arrays.asList(taskName);

        Response response = given()
                .header("Authorization","Bearer " + prop.getProperty("bearer_token"))
                .get()
                .then()
                    .statusCode(200)
                    .body("content",equalTo(tl)).body("id",notNullValue())
                .extract().response();

        List<String> resp_projectId = response.path("project_id");
        List<Long> projectIdList;
        projectIdList = Arrays.asList(projectId);

        assertEquals(projectIdList, resp_projectId);

        List<String> resp_taskId = response.path("id");
        return resp_taskId;
    }

    public void apiReopenTask(List<String> taskId, String endpoint){
        System.out.println("taskId: "+taskId);

        String taskIdStr = taskId.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
        System.out.println("taskId: "+taskIdStr);

        RestAssured.baseURI = "https://api.todoist.com/rest/v1";
        RestAssured.basePath = "tasks/"+taskIdStr+endpoint;

        System.out.println(baseURI+basePath);

        given()
                .header("Authorization","Bearer " + prop.getProperty("bearer_token"))
                .post()
                .then()
                .statusCode(204);
    }

}
