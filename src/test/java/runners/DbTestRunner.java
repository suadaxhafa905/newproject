package runners;

import core.utils.ReportCleaner;
import core.utils.RetryListener;
import core.utils.WordBugReportManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "stepdefinitions",
                "core.hooks"
        },
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumberReports/DbReport.html",
                "json:target/cucumberReports/DbReport.json"
        },
        tags = "@DB"
)
@Listeners(RetryListener.class)

public class DbTestRunner
        extends AbstractTestNGCucumberTests {

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