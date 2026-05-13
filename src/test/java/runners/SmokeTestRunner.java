package runners;

import core.utils.ReportCleaner;
import core.utils.RetryListener;
import core.utils.WordBugReportManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "stepdefinitions",
                "core.hooks"
        },
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumberReports/SmokeReport.html",
                "json:target/cucumberReports/SmokeReport.json"
        },
        tags = "@Smoke"
)
@Listeners(RetryListener.class)
public class SmokeTestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void cleanReportsBeforeSuite() {
        ReportCleaner.cleanReports();
    }

    @AfterSuite
    public void generateWordBugReport() {
        WordBugReportManager.generateReport();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}