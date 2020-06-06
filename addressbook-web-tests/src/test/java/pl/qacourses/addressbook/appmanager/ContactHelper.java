package pl.qacourses.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void homePage()  {
        click(By.linkText("home page"));
    }

    public void submit() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillForm(ContactFormData contactFormData, boolean creation) {
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

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }
   public void delete() {
        click(By.xpath("//input[@value='Delete']"));
   }


    public void acceptAlert() {
        try {
            wd.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert is present, error: " + e);
        }


    }

    public void initModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }
    public void submitModification() {
        click(By.name("update"));
    }

    public void create(ContactFormData contactFormData) {
        fillForm(contactFormData,true);
        submit();
        homePage();
    }

    public void edit(ContactFormData contactFormData) {
        select(contactFormData.getId());
        initModification();
        fillForm(contactFormData, false);
        submitModification();
    }


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactFormData> getContactList1() {
        List<ContactFormData> contacts = new ArrayList<ContactFormData>();
        List<WebElement> rows_table = wd.findElements(By.id("maintable"));

        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {
            //To locate columns(cells) of that specific row.
            List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

            String lastname = Columns_row.get(1).getText();
            String firstname = Columns_row.get(2).getText();
            //int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            ContactFormData contact = new ContactFormData().withId(row).withFirstname(firstname).withLastname(lastname);
            contacts.add(contact);
        }

        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        //List<ContactFormData> contacts = new ArrayList<ContactFormData>();
        List<WebElement> rows_table = wd.findElements(By.id("maintable"));

        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {
            //To locate columns(cells) of that specific row.
            List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

            String lastname = Columns_row.get(1).getText();
            String firstname = Columns_row.get(2).getText();
            ContactFormData contact = new ContactFormData().withId(row).withFirstname(firstname).withLastname(lastname);
            contacts.add(contact);
        }

        return contacts;
    }

    public void waiter() {
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void goHome() {
        if (!isElementPresent(By.name("MainForm"))) {
            wd.findElement(By.linkText("home")).click();
        }
    }



}
