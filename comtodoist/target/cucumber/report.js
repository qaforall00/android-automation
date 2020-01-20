$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("createNewProject.feature");
formatter.feature({
  "line": 1,
  "name": "Create new project, create new task, reopen exitsing task",
  "description": "",
  "id": "create-new-project,-create-new-task,-reopen-exitsing-task",
  "keyword": "Feature"
});
formatter.scenario({
  "comments": [
    {
      "line": 2,
      "value": "#  @Test1"
    },
    {
      "line": 3,
      "value": "#  Scenario: Verify new project is created successfully and listed in the Project list"
    },
    {
      "line": 4,
      "value": "#    Given I have created the new project \"Movies to watch by me\" via \"/projects\""
    },
    {
      "line": 5,
      "value": "#    And I login to the mobile application"
    },
    {
      "line": 6,
      "value": "#    Then I am able to see the project \"Movies to watch by me\" is listed in the project list"
    },
    {
      "line": 7,
      "value": "#    And I logout the mobile application successfully"
    },
    {
      "line": 9,
      "value": "#  @Test2"
    },
    {
      "line": 10,
      "value": "#  Scenario: Verify able to create new task successfully via mobile app"
    },
    {
      "line": 11,
      "value": "#    Given I login to the mobile application"
    },
    {
      "line": 12,
      "value": "#    And I have created the new project \"Movies to watch by me\" via \"/projects\""
    },
    {
      "line": 13,
      "value": "#    When I create the task \"watch spiderman\" via mobile application for project \"Movies to watch by me\""
    },
    {
      "line": 14,
      "value": "#    Then I am able to verify my task \"watch spiderman\" via \"/tasks\""
    },
    {
      "line": 15,
      "value": "#    And I logout the mobile application successfully"
    }
  ],
  "line": 18,
  "name": "Verify able to reopen task after completion",
  "description": "",
  "id": "create-new-project,-create-new-task,-reopen-exitsing-task;verify-able-to-reopen-task-after-completion",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 17,
      "name": "@Test3"
    }
  ]
});
formatter.step({
  "line": 19,
  "name": "I login to the mobile application",
  "keyword": "Given "
});
formatter.step({
  "line": 20,
  "name": "I have created the new project \"Movies to watch by me\" via \"/projects\"",
  "keyword": "And "
});
formatter.step({
  "line": 21,
  "name": "I create the task \"watch batman\" via mobile application for project \"Movies to watch by me\"",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "I get my taskId via \"/tasks\" to complete the task \"watch batman\"",
  "keyword": "When "
});
formatter.step({
  "line": 23,
  "name": "I reopen the completed task via \"/reopen\"",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "I am able to see the task \"watch batman\" is listed in the project \"Movies to watch\"",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDefinitions.i_login_to_mobile_application222()"
});
formatter.result({
  "duration": 11640952013,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Movies to watch by me",
      "offset": 32
    },
    {
      "val": "/projects",
      "offset": 60
    }
  ],
  "location": "StepDefinitions.i_have_created_the_new_project(String,String)"
});
formatter.result({
  "duration": 11056986598,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "watch batman",
      "offset": 19
    },
    {
      "val": "Movies to watch by me",
      "offset": 69
    }
  ],
  "location": "StepDefinitions.i_create_the_task_via_mobile_app(String,String)"
});
