package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.gotoAddNewContactForm();
    app.fillContactForm(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl"));
    app.submitNewContact();
    app.returnToHomePage();
  }


}

