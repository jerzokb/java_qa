package pl.qacourses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTestChrome extends TestBaseChrome {

    @Test
    public void testContactModification() {
        if(!app.contact().isThereAContact()) {
            app.contact().create(
                    new ContactFormData().withId(0).withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").withMobile("+48 123-123-123")
                            .withEmial("test@wp.pl"));
        }
        //List<ContactFormData> before = app.contact().getContactList1();
        Contacts before = app.contact().all();
        ContactFormData modifiedContact = before.iterator().next();
        //int index = before.get(before.size() - 1).getId();
        ContactFormData contact = new ContactFormData().withId(modifiedContact.getId()).withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").withMobile("+48 123-123-123")
                .withEmial("test@wp.pl");

        app.contact().edit(contact);

        app.contact().homePage();
        //List<ContactFormData> after = app.contact().getContactList1();
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size());

        // before.remove(before.size()-1);
        //before.add(contact);
        // Comparator<? super ContactFormData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        //before.sort(byId);
        //after.sort(byId);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        assertEquals(before, after);
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

        //app.getContactHelper().editContact(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", null));
    }

}
