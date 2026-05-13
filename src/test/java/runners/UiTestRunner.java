package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import core.utils.ReportCleaner;
import org.testng.annotations.BeforeSuite;
import core.utils.RetryListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import core.utils.WordBugReportManager;
import org.testng.annotations.AfterSuite;

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
       tags = "@Regression"
   //     tags = "@Api"
)
@Listeners(RetryListener.class)

public class UiTestRunner extends AbstractTestNGCucumberTests  {

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

