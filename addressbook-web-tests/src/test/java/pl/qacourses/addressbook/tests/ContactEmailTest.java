package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {

    @Test
    public void testContactEmail() {
        app.contact().goHome();
        ContactFormData contact = app.contact().all().iterator().next();
        ContactFormData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    public static String cleaned(String email){
        return email.replaceAll("\\s","").replaceAll("[-()]","");
    }

    public String mergeEmails(ContactFormData contact) {
        return Arrays.asList(contact.getEmailFirst(),contact.getEmailSecond(),contact.getEmailThird())
                .stream().filter((s -> !s.equals("")))
                .map(ContactEmailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

}
