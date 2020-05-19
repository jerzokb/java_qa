package pl.qacourses.addressbook.tests_contact;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model_contact.ContactFormData;

public class ContactCreationTest extends TestBaseContact {

  @Test
  public void testContactCreation() throws Exception {
    gotoAddNewContactForm();
    fillContactForm(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl"));
    submitNewContact();
    returnToHomePage();
  }


}

