package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdressTest extends TestBase {

    @Test
    public void testContactAddress() {
        app.contact().goHome();
        ContactFormData contact = app.contact().all().iterator().next();
        ContactFormData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(mergeAddresses(contactInfoFromEditForm)));
    }

    public String mergeAddresses(ContactFormData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
    }

}
