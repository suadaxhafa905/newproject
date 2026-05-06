package core.hooks;

import core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.LoginPage;


public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}




/*
public class Hooks {

    public static LoginPage loginPage;

    @Before
    public void setUp() {
        DriverFactory.initDriver();
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}

 */