package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTestsChrome extends TestBaseChrome {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
    }

}
