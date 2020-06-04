package pl.qacourses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTestChrome extends TestBaseChrome {

    @Test
    public void testContactModification() {
        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactFormData(0,"Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", "test1"));
        }
        List<ContactFormData> before = app.getContactHelper().getContactList1();
        ContactFormData contact = new ContactFormData(before.get(before.size() - 1).getId(),"Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", "test1");

        app.getContactHelper().editContact(contact);

        app.getContactHelper().returnToHomePage();
        List<ContactFormData> after = app.getContactHelper().getContactList1();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
        Comparator<? super ContactFormData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        Assert.assertEquals(before, after);

        //app.getContactHelper().editContact(new ContactFormData("Beata", "Jerzok", "Testowy Address", "+48 123-123-123", "test@wp.pl", null));
    }

}
