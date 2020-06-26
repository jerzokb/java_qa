package pl.qacourses.mantis.tests;

import org.openqa.selenium.remote.BrowserType;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.qacourses.mantis.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    //@BeforeMethod(alwaysRun = true)
    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    //@AfterMethod(alwaysRun = true)
    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

}
