package core.hooks;


import core.driver.DriverFactory;
import core.utils.ReportManager;
import core.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.LoginPage;


public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        DriverFactory.initDriver();

        ReportManager.startTest(scenario.getName());

        System.out.println("Starting Scenario: " + scenario.getName());

    }
/*
    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

 */
    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            ScreenshotUtil.takeScreenshot(
                    DriverFactory.getDriver(),
                    scenario.getName()
            );
        }
        else {

            ReportManager.logPass(
                    "Scenario Passed: " + scenario.getName()
            );
        }

        // Mbyll report
        ReportManager.flushReport();

        // Mbyll browser
        DriverFactory.quitDriver();

        System.out.println("Finished Scenario: " + scenario.getName());
    }
}

