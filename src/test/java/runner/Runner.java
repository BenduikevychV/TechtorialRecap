package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature/APIGetEmployeeCall.feature",
        plugin = {"pretty","html:target/cucumber-html-report","junit:target/cucumber.xml",
                "rerun:target/rerun.txt","json:target/cucumber.json"},
        glue = "steps",
        tags = "@api",
        dryRun = false
)
public class Runner {


}
