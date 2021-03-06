package pl.qacourses.addressbook.tests;

import com.google.common.collect.Table;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.qacourses.addressbook.appmanager.ApplicationManager;
import pl.qacourses.addressbook.model.Contacts;
import pl.qacourses.addressbook.model.GroupData;
import pl.qacourses.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);
   //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
   //protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
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
    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream().
                    map((g)->new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }

    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream().
                    map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }


}
