package pl.qacourses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;

import java.util.List;

public class ContactDeletionTestsChrome extends TestBaseChrome {

    @Test
    public void testContactDeletion() {
        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", "test1"));
        }
        List<ContactFormData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
        app.getContactHelper().returnToHomePage();

        List<ContactFormData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }

}
