package core.hooks;


import core.context.ExecutionInfo;
import core.context.ScenarioContext;
import core.driver.DriverFactory;
import core.utils.ReportManager;
import core.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.apache.logging.log4j.Logger;
import core.utils.LoggerHelper;

public class Hooks {


    private static final Logger logger =
            LoggerHelper.getLogger(Hooks.class);


    @Before
    public void setUp(Scenario scenario) {

        ReportManager.startTest(scenario.getName());

        if (isUiScenario(scenario)) {
            DriverFactory.initDriver();

            ExecutionInfo.setBrowserVersion(
                    ((org.openqa.selenium.HasCapabilities) DriverFactory.getDriver())
                            .getCapabilities()
                            .getBrowserVersion()
            );

            System.out.println(
                    "Browser version saved: "
                            + ExecutionInfo.getBrowserVersion()
            );
        }

   //     System.out.println("Starting Scenario: " + scenario.getName());

        logger.info(
                "Starting Scenario: {}",
                scenario.getName()
        );
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            if (DriverFactory.getDriver() != null) {

                String screenshotPath =
                        ScreenshotUtil.takeScreenshot(
                                DriverFactory.getDriver(),
                                scenario.getName()
                        );

                ReportManager.logFailWithScreenshot(
                        "Scenario Failed: " + scenario.getName(),
                        screenshotPath
                );

            } else {

                ReportManager.logFail(
                        "Scenario Failed: " + scenario.getName()
                );
            }

        }
        /*else {

            ReportManager.logPass(
                    "Scenario Passed: " + scenario.getName()
            );
        }

         */

        else {

            String screenshotPath = null;

            if (DriverFactory.getDriver() != null) {

                screenshotPath =
                        ScreenshotUtil.takeScreenshot(
                                DriverFactory.getDriver(),
                                scenario.getName() + "_PASS"
                        );

                ReportManager.logPassWithScreenshot(
                        "Scenario Passed: " + scenario.getName(),
                        screenshotPath
                );

            } else {

                ReportManager.logPass(
                        "Scenario Passed: " + scenario.getName()
                );
            }
        }

        ReportManager.flushReport();

        ScenarioContext.clear();

        if (DriverFactory.getDriver() != null) {
            DriverFactory.quitDriver();
        }

      //  System.out.println("Finished Scenario: " + scenario.getName());

        logger.info(
                "Finished Scenario: {}",
                scenario.getName()
        );
    }

    private boolean isUiScenario(Scenario scenario) {

        return !scenario.getSourceTagNames().contains("@Api")
                && !scenario.getSourceTagNames().contains("@DB")
                && !scenario.getSourceTagNames().contains("@Integration");
    }
}

/*
public class Hooks {


 */
    /*
    @Before
    public void setUp(Scenario scenario) {

        DriverFactory.initDriver();

        ReportManager.startTest(scenario.getName());

        System.out.println("Starting Scenario: " + scenario.getName());
    }

     */
/*
    @Before
    public void setUp(Scenario scenario) {

        ReportManager.startTest(scenario.getName());

        if (!scenario.getSourceTagNames().contains("@Api")
                && !scenario.getSourceTagNames().contains("@DB")
                && !scenario.getSourceTagNames().contains("@Integration")) {

            DriverFactory.initDriver();
        }

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
                    "Scenario Passed: " + scenario.getName());
        }

        ReportManager.flushReport();

        WordBugReportManager.generateReport();

        ScenarioContext.clear();

        DriverFactory.quitDriver();

        System.out.println("Finished Scenario: " + scenario.getName());
    }

}

 */
