package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;

public class ContactModificationTestChrome extends TestBaseChrome {

    @Test
    public void testContactModification() {
        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", "test1"));
        }
        app.getContactHelper().editContact(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", null));
    }

}
