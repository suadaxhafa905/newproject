package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = {"stepdefinitions",
                "core.driver",
                "core.hooks",
                "pages"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumberReports/HtmlReport.html",
                "json:target/cucumberReports/Cucumber.json",
                "junit:target/cucumberReports/Cucumber.xml",
                "rerun:target/cucumberReports/rerun.txt"
        },
        tags = "@suada"
)
public class TestRunner extends AbstractTestNGCucumberTests  {
}

