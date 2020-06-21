package pl.qacourses.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;
import pl.qacourses.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXML() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactFormData.class);
      List<ContactFormData> contacts = (List<ContactFormData>) xStream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactFormData> contacts = gson.fromJson(json, new TypeToken<List<ContactFormData>>(){}.getType());
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

  }

  @Test(dataProvider = "validContactsFromXML")
  public void testContactCreation(ContactFormData contact) throws Exception {
    app.goToContact().goToNewContactForm();
    Contacts before = app.contact().all();
    //ContactFormData contact =
    //        new ContactFormData().withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").
    //                withMobile("+48 123-123-123").withEmial("test@wp.pl").withPhoto(photo);
    app.contact().create(contact);

    Contacts after = app.contact().all();


    assertThat(after.size(), equalTo(before.size() + 1));


        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }

  @Test(dataProvider = "validContactsFromXML")
  public void testContactCreationDB(ContactFormData contact) throws Exception {
    app.goToContact().goToNewContactForm();
    Contacts before = app.db().contacts();
    //ContactFormData contact =
    //        new ContactFormData().withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").
    //                withMobile("+48 123-123-123").withEmial("test@wp.pl").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.
            withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testContactCreationWithPhoto() throws Exception {
    app.goToContact().goToNewContactForm();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/contact_img.png");

    ContactFormData contact =
            new ContactFormData().withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").
                    withMobile("+48 123-123-123").withEmial("test@wp.pl").withPhoto(photo);
    app.contact().create(contact);

    Contacts after = app.contact().all();


    assertThat(after.size(), equalTo(before.size() + 1));


    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }



}

