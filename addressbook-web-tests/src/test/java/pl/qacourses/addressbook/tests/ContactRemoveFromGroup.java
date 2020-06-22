package pl.qacourses.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;
import pl.qacourses.addressbook.model.GroupData;
import pl.qacourses.addressbook.model.Groups;

public class ContactRemoveFromGroup extends TestBase {
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
    public void testContactRemoveFromGroup() {
        app.contact().homePage();
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        ContactFormData removeContact = before.iterator().next();
        GroupData fromGroup = groups.iterator().next();
        app.contact().removeFromGroup(removeContact, fromGroup);
        verifyContactListInUI();
    }
}
