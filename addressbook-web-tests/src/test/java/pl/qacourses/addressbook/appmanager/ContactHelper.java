package pl.qacourses.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.qacourses.addressbook.model.ContactFormData;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactFormData contactFormData) {
        type(By.name("firstname"),contactFormData.getFirstname());
        type(By.name("lastname"),contactFormData.getLastname());
        type(By.name("address"),contactFormData.getAddress());
        type(By.name("mobile"),contactFormData.getMobile());
        type(By.name("email"),contactFormData.getEmial());
    }
}
