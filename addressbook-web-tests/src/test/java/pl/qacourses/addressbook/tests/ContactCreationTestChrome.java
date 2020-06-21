package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTestChrome extends TestBaseChrome {

    @Test
    public void testContactCreation() throws Exception {
        app.goToContact().goToNewContactForm();
        //List<ContactFormData> before = app.contact().getContactList1();
        Contacts before = app.contact().all();
        ContactFormData contact =
                new ContactFormData().withFirstname("Beata").withLastname("Jerzok").withAddress("Testowy Address").withMobile("+48 123-123-123").withEmial("test@wp.pl");
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


}
