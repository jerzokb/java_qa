package pl.qacourses.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactNavigationHelper {
    protected WebDriver wd;

    public ContactNavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void goToNewContactForm() {
        wd.findElement(By.linkText("add new")).click();
    }





}
