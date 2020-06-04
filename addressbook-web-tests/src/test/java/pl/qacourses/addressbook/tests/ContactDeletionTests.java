package pl.qacourses.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {

        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", "test1"));
        }
        //int before = app.getContactHelper().getGroupCount();
        List<ContactFormData> before = app.getContactHelper().getContactList1();

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();


        app.getContactHelper().acceptAlert();
        app.getContactHelper().goHome();
        app.getContactHelper().waiter();
        //app.getContactHelper().waitForMessage();
        //app.getContactHelper().homePage();

        //int after = app.getContactHelper().getGroupCount();
        List<ContactFormData> after = app.getContactHelper().getContactList1();
       Assert.assertEquals(after.size(), before.size());

       //before.remove(before.size()-1);
       Assert.assertEquals(before, after);

        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }

}
