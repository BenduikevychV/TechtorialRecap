package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature/APIGetEmployeeCall.feature",
        glue = "steps",
        tags = "@api",
        dryRun = false
)
public class Runner {


}
