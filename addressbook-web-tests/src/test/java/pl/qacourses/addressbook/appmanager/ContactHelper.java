package pl.qacourses.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public void createContact(ContactFormData contactFormData) {
        fillContactForm(contactFormData,true);
        submitNewContact();
        returnToHomePage();
    }

    public void editContact(ContactFormData contactFormData) {
       // selectContact();
        initContactModification();
        fillContactForm(contactFormData, false);
        submitContactModification();
    }


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactFormData> getContactList() {
        List<ContactFormData> contacts = new ArrayList<ContactFormData>();
        //List<WebElement> rows_table = wd.findElements(By.name("entry"));
        List<WebElement> rows_table = wd.findElements(By.id("maintable"));
        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {
            //To locate columns(cells) of that specific row.
            List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

            String lastname = Columns_row.get(1).getText();
            String firstname = Columns_row.get(2).getText();
           // int id = Integer.parseInt(Columns_row.findElement(By.tagName("input")).getAttribute("value"));
            ContactFormData contact = new ContactFormData(row, firstname, lastname, null, null, null, null);
            contacts.add(contact);
        }

        return contacts;
    }
}
