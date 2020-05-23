package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.GroupData;

public class GroupModificationTestChrome extends TestBaseChrome {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));

        }
        app.getGroupHelper().editGroup(new GroupData("test1", "test2", "test3"));
    }
}
