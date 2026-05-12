package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import core.utils.ReportCleaner;
import org.testng.annotations.BeforeSuite;
import core.utils.RetryListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

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
     //   tags = "@RegisterNegative"
        tags = "@Api"
)
@Listeners(RetryListener.class)



public class TestRunner_Login extends AbstractTestNGCucumberTests  {

        @BeforeSuite
        public void cleanReportsBeforeSuite() {
                ReportCleaner.cleanReports();
        }


        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}

