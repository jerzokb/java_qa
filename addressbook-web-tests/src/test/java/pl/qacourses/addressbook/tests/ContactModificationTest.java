package pl.qacourses.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;
import pl.qacourses.addressbook.model.Groups;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().homePage();
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.contact().create(new ContactFormData().withId(0).withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").withMobile("+48 123-123-123")
                    .withEmial("test@wp.pl").inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactModification() {
        app.contact().homePage();
        Contacts before = app.contact().all();
        ContactFormData modifiedContact = before.iterator().next();
        ContactFormData contact = new ContactFormData().withId(modifiedContact.getId()).withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").withMobile("+48 123-123-123")
                .withEmial("test@wp.pl");

        app.contact().edit(contact);

        app.contact().homePage();

        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size());

        assertEquals(before, after);
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

    }


    @Test
    public void testContactModificationDB() {
        app.contact().homePage();
        Contacts before = app.db().contacts();
        ContactFormData modifiedContact = before.iterator().next();
        //int index = before.get(before.size() - 1).getId();
        ContactFormData contact = new ContactFormData().withId(modifiedContact.getId()).withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").withMobile("+48 123-123-123")
                .withEmial("test@wp.pl");
        app.contact().homePage();
        app.contact().edit(contact);
        app.contact().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        //List<ContactFormData> after = app.contact().getContactList1();
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

    }
}
