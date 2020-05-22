package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;

public class ContactCreationTestChrome extends TestBaseChrome {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactNavigationHelper().gotoAddNewContactForm();
        app.getContactHelper().fillContactForm(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", "test1"), true);
        app.getContactHelper().submitNewContact();
        app.getContactHelper().returnToHomePage();
    }


}
