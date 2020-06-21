package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {

        if(!app.contact().isThereAContact()) {
            app.contact().create(
                    new ContactFormData().withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").withMobile("+48 123-123-123").withEmial("test@wp.pl"));
        }
        //int before = app.getContactHelper().getGroupCount();
        //List<ContactFormData> before = app.contact().getContactList1();
        Contacts before = app.contact().all();
        ContactFormData deletedContact = before.iterator().next();

        //app.contact().selectContact(before.size() - 1);
        app.contact().selectById(deletedContact.getId());
        app.contact().delete();


        app.contact().acceptAlert();
        app.contact().goHome();
        app.contact().waiter();
        //app.getContactHelper().waitForMessage();
        //app.getContactHelper().homePage();

        //int after = app.getContactHelper().getGroupCount();
        //List<ContactFormData> after = app.contact().getContactList1();
        Contacts after = app.contact().all();
       assertEquals(after.size(), before.size());

       //before.remove(before.size()-1);
       //Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.withOut(deletedContact)));

       //Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }

    @Test
    public void testContactDeletionDB() {

        if(!app.contact().isThereAContact()) {
            app.contact().create(
                    new ContactFormData().withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").withMobile("+48 123-123-123").withEmial("test@wp.pl"));
        }

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
