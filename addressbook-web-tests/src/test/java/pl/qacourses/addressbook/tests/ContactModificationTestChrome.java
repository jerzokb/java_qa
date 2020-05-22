package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;

public class ContactModificationTestChrome extends TestBaseChrome {

    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", null), false);
        app.getContactHelper().submitContactModification();

    }

}
