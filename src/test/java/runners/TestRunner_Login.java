package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions",
                "core.hooks"
        },
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumberReports/HtmlReport.html",
                "json:target/cucumberReports/Cucumber.json",
                "junit:target/cucumberReports/Cucumber.xml",
                "rerun:target/cucumberReports/rerun.txt"
        },
        tags = "@RegisterNegative"
)
public class TestRunner_Login extends AbstractTestNGCucumberTests  {
}

