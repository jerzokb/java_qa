package pl.qacourses.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", "test1"));
        }

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
    }

}
