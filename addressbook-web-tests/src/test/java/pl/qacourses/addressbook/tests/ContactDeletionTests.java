package pl.qacourses.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;
import pl.qacourses.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {
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
    public void testContactDeletion() {

        Contacts before = app.contact().all();
        ContactFormData deletedContact = before.iterator().next();

        app.contact().selectById(deletedContact.getId());
        app.contact().delete();

        app.contact().acceptAlert();
        app.contact().goHome();
        app.contact().waiter();

        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.withOut(deletedContact)));


    }

    @Test
    public void testContactDeletionDB() {


        Contacts before = app.db().contacts();
        ContactFormData deletedContact = before.iterator().next();

        app.contact().selectById(deletedContact.getId());
        app.contact().delete();


        app.contact().acceptAlert();
        app.contact().goHome();
        app.contact().waiter();

        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(deletedContact)));

    }

}
