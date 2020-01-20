import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="/Users/lgoh/Downloads/comtodoist/src/test/resources/features",
        glue="stepDefs",
        tags= "@Test1,@Test2,@Test3",
        plugin={"pretty", "html:target/cucumber", "json:target/cucumber.json", "junit:target/cukes.xml", "json:target/jsonReports/cucumber-report.json"})
public class TestRunner {

}
