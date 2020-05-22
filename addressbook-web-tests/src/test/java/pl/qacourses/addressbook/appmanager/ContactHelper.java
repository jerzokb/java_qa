package pl.qacourses.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

    public void fillContactForm(ContactFormData contactFormData, boolean creation) {
        type(By.name("firstname"),contactFormData.getFirstname());
        type(By.name("lastname"),contactFormData.getLastname());
        type(By.name("address"),contactFormData.getAddress());
        type(By.name("mobile"),contactFormData.getMobile());
        type(By.name("email"),contactFormData.getEmial());

        if (creation) {
            new Select (wd.findElement(By.name("new_group"))).selectByVisibleText(contactFormData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void selectContact() {
        click(By.name("selected[]"));
    }
   public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
   }
    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }
    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }
    public void submitContactModification() {
        click(By.name("update"));
    }
}
