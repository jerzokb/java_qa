package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.ContactFormData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTest extends TestBase {

    @Test
    public void testContactDetails() {
        app.contact().goHome();
        ContactFormData contact = app.contact().all().iterator().next();
        ContactFormData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactFormData contactDetailsInfoForm = app.contact().infoFromDetails(contact);
        assertThat(margeDetails(contactInfoFromEditForm), equalTo(cleaned(contactDetailsInfoForm.getName())));
    }

    private String margePhonesDetails(ContactFormData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((p) -> !p.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public String mergeEmails(ContactFormData contact) {
        return Arrays.asList(contact.getEmailFirst(),contact.getEmailSecond(),contact.getEmailThird())
                .stream().filter((s -> !s.equals("")))
                .map(ContactEmailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String margeNameAndSurname(ContactFormData contact) {
        return Arrays.asList((contact.getFirstname() + " " + contact.getLastname())
                .trim(), contact.getAddress())
                .stream().filter((n) -> !n.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String margeDetails(ContactFormData contact) {
        return Arrays.asList(margeNameAndSurname(contact),
                margePhonesDetails(contact), mergeEmails(contact))
                .stream().filter((d) -> !d.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public String cleaned(String phone) {
        return phone.replaceAll("[A-Z]: ", "")
                .replaceAll("\n\n", "\n")
                .replaceAll("\n\nMember of: [A-Z,a-z]* *[0-9]*","");
    }
}
