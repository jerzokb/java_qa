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
        attach(By.name("photo"),contactFormData.getPhoto());

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
        selectById(contactFormData.getId());
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
            String address = Columns_row.get(3).getText();
            String allEmails = Columns_row.get(4).getText();
            String allPhones = Columns_row.get(5).getText();
            ContactFormData contact = new ContactFormData().withId(row).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones);
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


    public ContactFormData infoFromEditForm(ContactFormData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactFormData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address)
                .withEmailFirst(email1).withEmailSecond(email2).withEmailThird(email3)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

//    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public ContactFormData infoFromDetails(ContactFormData contact) {
        initContactDetailsById(contact.getId());
        String detailName = wd.findElement(By.id("content")).getText();
        return new ContactFormData().withId(contact.getId()).withName(detailName);
    }

    private void initContactDetailsById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(6).findElement(By.tagName("a")).click();
    }
}
