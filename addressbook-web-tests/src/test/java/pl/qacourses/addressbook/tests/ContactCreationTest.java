package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goToContact().goToNewContactForm();
    //List<ContactFormData> before = app.contact().getContactList1();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/contact_img.png");
    //ContactFormData contact =
           // new ContactFormData().withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").withMobile("+48 123-123-123").withEmial("test@wp.pl").withGroup("test1");
    ContactFormData contact =
            new ContactFormData().withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").
                    withMobile("+48 123-123-123").withEmial("test@wp.pl").withPhoto(photo);
    app.contact().create(contact);
    //List<ContactFormData> after = app.contact().getContactList1();
    Contacts after = app.contact().all();
    //Assert.assertEquals(after.size(), before.size() + 1);

    assertThat(after.size(), equalTo(before.size() + 1));


    //contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    //before.add(contact);
    //Comparator<? super ContactFormData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    //before.sort(byId);
    //after.sort(byId);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }

 /* @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/contact_img.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());

  }*/


}

