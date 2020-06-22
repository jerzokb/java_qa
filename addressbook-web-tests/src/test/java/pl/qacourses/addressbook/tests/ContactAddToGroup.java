package pl.qacourses.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;
import pl.qacourses.addressbook.model.Contacts;
import pl.qacourses.addressbook.model.GroupData;
import pl.qacourses.addressbook.model.Groups;

public class ContactAddToGroup extends TestBase {

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
    public void testContactAddToGroup() {
        app.contact().homePage();
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        ContactFormData addContact = before.iterator().next();
        GroupData toGroup = groups.iterator().next();
        app.contact().addToGroup(addContact, toGroup);
        verifyContactListInUI();

    }

}
