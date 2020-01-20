Feature: Create new project, create new task, reopen exitsing task
  @Test1
  Scenario: Verify new project is created successfully and listed in the Project list
    Given I have created the new project "Movies to watch by me" via "/projects"
    And I login to the mobile application
    Then I am able to see the project "Movies to watch by me" is listed in the project list
    And I logout the mobile application successfully

  @Test2
  Scenario: Verify able to create new task successfully via mobile app
    Given I login to the mobile application
    And I have created the new project "Movies to watch by me" via "/projects"
    When I create the task "watch spiderman" via mobile application for project "Movies to watch by me"
    Then I am able to verify my task "watch spiderman" via "/tasks"
    And I logout the mobile application successfully

  @Test3
  Scenario: Verify able to reopen task after completion
    Given I login to the mobile application
    And I have created the new project "Movies to watch by me" via "/projects"
    And I create the task "watch batman" via mobile application for project "Movies to watch by me"
    When I get my taskId via "/tasks" to complete the task "watch batman"
    And I reopen the completed task via "/reopen"
    Then I am able to see the task "watch batman" is listed in the project "Movies to watch"
    And I logout the mobile application successfully
