package core.hooks;


import core.driver.DriverFactory;
import core.utils.ReportManager;
import core.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {

        DriverFactory.initDriver();

        ReportManager.startTest(scenario.getName());

        System.out.println("Starting Scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            String screenshotPath = ScreenshotUtil.takeScreenshot(
                    DriverFactory.getDriver(),
                    scenario.getName()
            );

            ReportManager.logFailWithScreenshot(
                    "Scenario Failed: " + scenario.getName(),
                    screenshotPath
            );

        } else {

            ReportManager.logPass(
                    "Scenario Passed: " + scenario.getName()
            );
        }

        ReportManager.flushReport();

        DriverFactory.quitDriver();

        System.out.println("Finished Scenario: " + scenario.getName());
    }

}
